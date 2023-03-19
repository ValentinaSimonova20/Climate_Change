package com.simonova.ecoinformerapp.services.weather;

import com.simonova.ecoinformerapp.model.WeatherDailyData;
import com.simonova.ecoinformerapp.model.WeatherRequest;
import com.simonova.ecoinformerapp.model.WeatherSeasonData;

import java.util.List;
import java.util.Map;

public interface WeatherService {
    WeatherDailyData getWeatherDailyData(WeatherRequest weatherRequest);
    WeatherSeasonData getWeatherSeasonData(WeatherRequest weatherRequest);
    List<String> getAllSeasonNames();
    List<String> getAllTempNames();
    Map<String, Map<String, List<String>>> getSeasonInfoForUi(WeatherSeasonData weatherSeasonData);
}
