package com.simonova.weatherapp.service.weather;

import com.simonova.weatherapp.model.*;
import com.simonova.weatherapp.service.coordinates.CoordinatesService;
import com.simonova.weatherapp.service.temperature.TemperatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final CoordinatesService coordinatesService;
    private final TemperatureService temperatureService;

    @Override
    public ResultWeatherDailyData getWeatherDailyData(WeatherRequest weatherRequest) {
        WeatherDailyData result = getWeatherDailyData(
                getLocationInfoByAddress(weatherRequest.getCity()),
                weatherRequest.getStartDate(),
                weatherRequest.getEndDate()
        );

        return
                new ResultWeatherDailyData()
                        .data(
                                result
                                        .getData()
                                        .stream()
                                        .map(tempDailyInfo -> {
                                            String[] infos = tempDailyInfo.getDate().split("-");
                                            return
                                                    new ResultTemperatureDailyInfo()
                                                            .year(Integer.valueOf(infos[0]))
                                                            .month(Integer.valueOf(infos[1]))
                                                            .day(Integer.valueOf(infos[2]))
                                                            .tmax(tempDailyInfo.getTmax());
                                        }).toList()
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

}
