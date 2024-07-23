package com.simonova.ecoinformerapp.services.weather;

import com.simonova.ecoinformerapp.model.WeatherDailyData;
import com.simonova.ecoinformerapp.model.WeatherRequest;

import java.time.Month;
import java.util.List;

public interface WeatherService {
    WeatherDailyData getWeatherDailyData(WeatherRequest weatherRequest);

    List<Double[]> getWeatherDailyDataByMonth(WeatherDailyData weatherDailyData, Month month);
    String[] getHeaders(WeatherDailyData weatherDailyData);
}
