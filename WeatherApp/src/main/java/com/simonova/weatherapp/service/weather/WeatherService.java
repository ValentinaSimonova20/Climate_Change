package com.simonova.weatherapp.service.weather;

import com.simonova.weatherapp.model.WeatherDailyData;
import com.simonova.weatherapp.model.WeatherRequest;
import com.simonova.weatherapp.model.WeatherSeasonData;

public interface WeatherService {
    WeatherDailyData getWeatherDailyData(WeatherRequest weatherRequest);
    WeatherSeasonData getWeatherSeasonData(WeatherRequest weatherRequest);

}
