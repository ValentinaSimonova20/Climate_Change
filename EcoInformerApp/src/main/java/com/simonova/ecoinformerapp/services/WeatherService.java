package com.simonova.ecoinformerapp.services;

import com.simonova.ecoinformerapp.model.WeatherDailyData;
import com.simonova.ecoinformerapp.model.WeatherRequest;

public interface WeatherService {
    WeatherDailyData getWeatherDailyData(WeatherRequest weatherRequest);
}
