package com.simonova.ecoinformerapp.services.weather;

import com.simonova.ecoinformerapp.integration.api.WeatherApi;
import com.simonova.ecoinformerapp.model.WeatherDailyData;
import com.simonova.ecoinformerapp.model.WeatherRequest;
import com.simonova.ecoinformerapp.model.WeatherSeasonData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherApi weatherApi;

    private static final String WINTER = "Winter";
    private static final String SPRING = "Spring";
    private static final String SUMMER = "Summer";
    private static final String AUTUMN = "Autumn";
    private static final String MIN_TEMP = "Min temp";
    private static final String MAX_TEMP = "Max temp";

    @Override
    public WeatherDailyData getWeatherDailyData(WeatherRequest weatherRequest) {
        return weatherApi.getDailyWeatherInfo(
                weatherRequest.getCity(),
                weatherRequest.getStartDate(),
                weatherRequest.getEndDate()
        );
    }

    @Override
    public WeatherSeasonData getWeatherSeasonData(WeatherRequest weatherRequest) {
        return weatherApi.getSeasonWeatherInfo(
                weatherRequest.getCity(),
                weatherRequest.getStartDate(),
                weatherRequest.getEndDate()
        );
    }

    @Override
    public List<String> getAllSeasonNames() {
        return Arrays.asList(WINTER, SPRING, SUMMER, AUTUMN);
    }

    @Override
    public List<String> getAllTempNames() {
        return Arrays.asList(MIN_TEMP, MAX_TEMP);
    }

    /**
     * @param weatherSeasonData
     * @return weather info in dict - {year1 : {season1 : {min temp, max temp}, season2 : {},...}, year2: {},...}
     */
    public Map<String, Map<String, List<String>>> getSeasonInfoForUi(WeatherSeasonData weatherSeasonData) {
        Map<String, Map<String, List<String>>> result = new TreeMap<>();
        weatherSeasonData.getData().forEach(tempInfo -> {
            Map<String, List<String>> seasonInfo =
                    result.containsKey(tempInfo.getYear()) ? result.get(tempInfo.getYear()) : new HashMap<>();
            seasonInfo.put(tempInfo.getSeason(), Arrays.asList(tempInfo.getMinTemp(), tempInfo.getMaxTemp()));
            result.put(tempInfo.getYear(), seasonInfo);
        });
        return result;
    }
}
