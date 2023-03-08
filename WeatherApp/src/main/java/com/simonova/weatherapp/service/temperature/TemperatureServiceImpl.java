package com.simonova.weatherapp.service.temperature;

import com.simonova.weatherapp.integration.api.WeatherApi;
import com.simonova.weatherapp.model.WeatherDailyData;
import com.simonova.weatherapp.service.apiKey.ApiKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemperatureServiceImpl implements TemperatureService {

    private final WeatherApi weatherApi;
    private static final String METEOSTAT_HOST = "meteostat.p.rapidapi.com";

    @Value("${weather.api.key}")
    private String weatherApiKey;

    @Override
    public WeatherDailyData getWeatherDailyData(String lat, String lon, String startDate, String endDate) {
        return weatherApi.getWeather(weatherApiKey, METEOSTAT_HOST, lat, lon, startDate, endDate);
    }
}
