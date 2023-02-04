package com.simonova.weatherapp.service.weather;

import com.simonova.weatherapp.model.WeatherDailyData;
import com.simonova.weatherapp.model.WeatherRequest;
import com.simonova.weatherapp.model.WeatherSeasonData;

import java.util.List;
import java.util.Map;

public interface WeatherService {
    WeatherDailyData getWeatherDailyData(WeatherRequest weatherRequest);
    WeatherSeasonData getWeatherSeasonData(WeatherRequest weatherRequest);
    List<String> getAllSeasonNames();
    List<String> getAllTempNames();
    Map<String, Map<String, List<String>>> getSeasonInfoForUi(WeatherSeasonData weatherSeasonData);

}
