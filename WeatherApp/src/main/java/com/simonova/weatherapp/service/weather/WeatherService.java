package com.simonova.weatherapp.service.weather;

import com.simonova.weatherapp.model.ResultWeatherDailyData;
import com.simonova.weatherapp.model.WeatherRequest;

public interface WeatherService {
    ResultWeatherDailyData getWeatherDailyData(WeatherRequest weatherRequest);
}
