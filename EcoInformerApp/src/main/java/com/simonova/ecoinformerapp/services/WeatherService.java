package com.simonova.ecoinformerapp.services;

import com.simonova.ecoinformerapp.model.WeatherDailyData;
import com.simonova.ecoinformerapp.model.WeatherRequest;
import com.simonova.ecoinformerapp.model.WeatherSeasonData;

public interface WeatherService {
    WeatherDailyData getWeatherDailyData(WeatherRequest weatherRequest);
    WeatherSeasonData getWeatherSeasonData(WeatherRequest weatherRequest);
}
