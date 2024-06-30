package com.simonova.weatherapp.service.weather;

import com.simonova.weatherapp.model.WeatherDailyData;
import com.simonova.weatherapp.model.WeatherRequest;

public interface WeatherService {
    WeatherDailyData getWeatherDailyData(WeatherRequest weatherRequest);
}
