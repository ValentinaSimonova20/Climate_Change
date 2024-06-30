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

}
