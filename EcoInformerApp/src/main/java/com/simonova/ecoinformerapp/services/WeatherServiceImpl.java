package com.simonova.ecoinformerapp.services;

import com.simonova.ecoinformerapp.integration.api.WeatherApi;
import com.simonova.ecoinformerapp.model.WeatherDailyData;
import com.simonova.ecoinformerapp.model.WeatherRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherApi weatherApi;

    @Override
    public WeatherDailyData getWeatherDailyData(WeatherRequest weatherRequest) {
        return weatherApi.getDailyWeatherInfo(weatherRequest.getCity(), weatherRequest.getStartDate(), weatherRequest.getEndDate());
    }
}
