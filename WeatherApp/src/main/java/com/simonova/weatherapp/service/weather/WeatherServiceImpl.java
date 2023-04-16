package com.simonova.weatherapp.service.weather;

import com.simonova.weatherapp.model.*;
import com.simonova.weatherapp.service.coordinates.CoordinatesService;
import com.simonova.weatherapp.service.temperature.TemperatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final CoordinatesService coordinatesService;
    private final TemperatureService temperatureService;
    private static final String WINTER = "Winter";
    private static final String SPRING = "Spring";
    private static final String SUMMER = "Summer";
    private static final String AUTUMN = "Autumn";

    @Override
    public WeatherDailyData getWeatherDailyData(WeatherRequest weatherRequest) {
        return getWeatherDailyData(
                getLocationInfoByAddress(weatherRequest.getCity()),
                weatherRequest.getStartDate(),
                weatherRequest.getEndDate()
        );
    }

    private WeatherDailyData getWeatherDailyData(LocationInfo locationInfo, String startDate, String endDate) {
        return temperatureService.getWeatherDailyData(
                locationInfo.getLatitude(),
                locationInfo.getLongitude(),
                startDate,
                endDate
        );
    }

    private LocationInfo getLocationInfoByAddress(String address) {
        return coordinatesService.getCoordinatesByAddress(address);
    }

    @Override
    public WeatherSeasonData getWeatherSeasonData(WeatherRequest weatherRequest) {
        List<TemperatureInfoBySeason> temperatureInfoBySeasons = getTempInfoConnectedToSeason(weatherRequest);

        // connect three result
        return new WeatherSeasonData().data(
                getTemperaturesBySeason(
                        getAvgTempBySeasonAndYear(temperatureInfoBySeasons),
                        getTemperaturesBySeason(
                                getMaxTempBySeasonAndYear(temperatureInfoBySeasons),
                                getTemperaturesBySeason(
                                        getMinTempBySeasonAndYear(temperatureInfoBySeasons),
                                        new ArrayList<>(),
                                        TemperatureInfoBySeason::setMinTemp
                                ),
                                TemperatureInfoBySeason::setMaxTemp
                        ),
                        TemperatureInfoBySeason::setAvgTemp
                ));
    }


    private List<TemperatureInfoBySeason> getTemperaturesBySeason(
            Map<SeasonYearModel,  String> mapToAddIntoResultList,
            List<TemperatureInfoBySeason> resultList,
            BiConsumer<TemperatureInfoBySeason, String> consumer
    ) {
        mapToAddIntoResultList.forEach((key, value) -> getYearAndSeasonObject(key, resultList).ifPresentOrElse(
                yearAndSeason -> consumer.accept(yearAndSeason, value),
                () -> {
                    TemperatureInfoBySeason newObject = new TemperatureInfoBySeason()
                            .season(key.getSeason())
                            .year(key.getYear());
                    consumer.accept(newObject, value);
                    resultList.add(newObject);
                }));
        return resultList;
    }

    private Optional<TemperatureInfoBySeason> getYearAndSeasonObject(
            SeasonYearModel key,
            List<TemperatureInfoBySeason> resultArray
    ) {
        return resultArray.stream().filter(temperatureInfoBySeason ->
                temperatureInfoBySeason.getSeason().equals(key.getSeason()) &&
                        temperatureInfoBySeason.getYear().equals(key.getYear())
        ).findFirst();
    }

    /**
     * @return min temp of min temps in format: SeasonYearModel - min temp
     */
    private Map<SeasonYearModel, String>  getMinTempBySeasonAndYear(List<TemperatureInfoBySeason> weatherInfo) {
        return getValueFromSummarize(
                getDefaultSummarize(
                        weatherInfo,
                        temp -> Double.parseDouble(temp.getMinTemp())
                ), temp -> String.valueOf(temp.getValue().getMin())
        );
    }

    /**
     * @return max temp of max temps in format: SeasonYearModel - max temp
     */
    private Map<SeasonYearModel, String> getMaxTempBySeasonAndYear(List<TemperatureInfoBySeason> weatherInfo) {
        return getValueFromSummarize(
                getDefaultSummarize(
                        weatherInfo,
                        temp -> temp != null ? Double.parseDouble(temp.getMaxTemp()) : 0.0
                ), temp -> String.valueOf(temp.getValue().getMax())
        );
    }

    /**
     * @return avg temp of avg temps in format: SeasonYearModel - avg temp
     */
    private Map<SeasonYearModel, String> getAvgTempBySeasonAndYear(List<TemperatureInfoBySeason> weatherInfo) {
        return getValueFromSummarize(
                getDefaultSummarize(
                        weatherInfo,
                        temp -> temp.getAvgTemp() != null ? Double.parseDouble(temp.getAvgTemp()) : Double.POSITIVE_INFINITY
                ), temp -> String.valueOf(temp.getValue().getAverage())
        );
    }

    /**
     * @return key and certain value from summarize statistics
     */
    private Map<SeasonYearModel, String> getValueFromSummarize(
            Map<SeasonYearModel, DoubleSummaryStatistics> summarize,
            Function<Map.Entry<SeasonYearModel, DoubleSummaryStatistics>, String> valueMapper
    ) {
        return summarize
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, valueMapper));
    }

    /**
     * @return key and its summarize statistics by certain parameter
     */
    private Map<SeasonYearModel, DoubleSummaryStatistics> getDefaultSummarize(
            List<TemperatureInfoBySeason> weatherInfo,
            ToDoubleFunction<TemperatureInfoBySeason> mapper
    ) {
        return weatherInfo.stream().collect(
                Collectors.groupingBy(
                        temperatureInfoBySeason -> Arrays.asList(
                                temperatureInfoBySeason.getSeason(),
                                temperatureInfoBySeason.getYear()
                        ),
                        Collectors.summarizingDouble(mapper)
                )
        ).entrySet().stream().collect(Collectors.toMap(
                entrySet -> new SeasonYearModel().season(entrySet.getKey().get(0)).year(entrySet.getKey().get(1)),
                Map.Entry::getValue
                ));
    }

    // get dailyInfo temperature. There are duplicates in season and year in result
    private List<TemperatureInfoBySeason> getTempInfoConnectedToSeason(WeatherRequest weatherRequest) {
        return getWeatherDailyData(weatherRequest).getData().stream()
                .map(temperatureDailyInfo ->
                        new TemperatureInfoBySeason()
                                .season(getSeasonOfDate(temperatureDailyInfo.getDate()))
                                .year(getYearOfDate(temperatureDailyInfo.getDate()))
                                .maxTemp(temperatureDailyInfo.getTmax())
                                .minTemp(temperatureDailyInfo.getTmin())
                                .avgTemp(temperatureDailyInfo.getTavg())
                ).collect(Collectors.toList());

    }

    private String getSeasonOfDate(String date) {
        Month month = LocalDate.parse(date).getMonth();

        if(month == Month.DECEMBER || month == Month.JANUARY || month == Month.FEBRUARY) {
            return WINTER;
        }
        if(month == Month.MARCH || month == Month.APRIL || month == Month.MAY) {
            return SPRING;
        }
        if(month == Month.JUNE || month == Month.JULY || month == Month.AUGUST) {
            return SUMMER;
        }
        return AUTUMN;
    }

    private String getYearOfDate(String date) {
        return String.valueOf(LocalDate.parse(date).getYear());
    }

}
