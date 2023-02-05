package com.simonova.weatherapp.controllers;

import com.simonova.weatherapp.model.WeatherDailyData;
import com.simonova.weatherapp.model.WeatherRequest;
import com.simonova.weatherapp.model.WeatherSeasonData;
import com.simonova.weatherapp.service.weather.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class GetWeatherInfoController {

    private WeatherService weatherService;

    @GetMapping("/getDailyWeatherInfo")
    public @ResponseBody WeatherDailyData getWeatherInfo(WeatherRequest weatherRequest) {
        return weatherService.getWeatherDailyData(weatherRequest);
    }

    @GetMapping("/getSeasonWeatherInfo")
    public @ResponseBody WeatherSeasonData getWeatherSeasonData(WeatherRequest weatherRequest) {
        return weatherService.getWeatherSeasonData(weatherRequest);
    }
}
