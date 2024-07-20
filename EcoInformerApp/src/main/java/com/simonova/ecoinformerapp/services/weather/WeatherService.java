package com.simonova.ecoinformerapp.services.weather;

import com.simonova.ecoinformerapp.model.WeatherDailyData;
import com.simonova.ecoinformerapp.model.WeatherRequest;
import com.simonova.ecoinformerapp.model.WeatherSeasonData;

import java.util.List;
import java.util.Map;

public interface WeatherService {
    WeatherDailyData getWeatherDailyData(WeatherRequest weatherRequest);

    List<List<Object>> getJanuaryWeatherDailyData(WeatherDailyData weatherDailyData);
    String[] getHeaders(WeatherDailyData weatherDailyData);
}
