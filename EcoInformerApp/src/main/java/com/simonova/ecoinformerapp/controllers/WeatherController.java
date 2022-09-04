package com.simonova.ecoinformerapp.controllers;

import com.simonova.ecoinformerapp.model.WeatherDailyData;
import com.simonova.ecoinformerapp.model.WeatherRequest;
import com.simonova.ecoinformerapp.services.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/dailyweather")
    public @ResponseBody WeatherDailyData getWeatherDailyInfo(WeatherRequest weatherRequest) {
        return weatherService.getWeatherDailyData(weatherRequest);
    }
}
