package com.simonova.ecoinformerapp.services.weather;

import com.simonova.ecoinformerapp.model.WeatherDailyData;
import com.simonova.ecoinformerapp.model.WeatherRequest;

import java.util.List;

public interface WeatherService {
    WeatherDailyData getWeatherDailyData(WeatherRequest weatherRequest);

    List<Double[]> getJanuaryWeatherDailyData(WeatherDailyData weatherDailyData);
    String[] getHeaders(WeatherDailyData weatherDailyData);
}
