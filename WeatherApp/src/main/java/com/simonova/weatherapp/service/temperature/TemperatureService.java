package com.simonova.weatherapp.service.temperature;

import com.simonova.weatherapp.requests.weather.model.WeatherDailyData;

public interface TemperatureService {
    WeatherDailyData getWeatherDailyData(String lat, String lon, String startDate, String endDate);
}
