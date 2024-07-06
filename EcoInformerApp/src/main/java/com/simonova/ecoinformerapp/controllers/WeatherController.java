package com.simonova.ecoinformerapp.controllers;

import com.simonova.ecoinformerapp.model.TemperatureDailyInfo;
import com.simonova.ecoinformerapp.model.WeatherRequest;
import com.simonova.ecoinformerapp.services.weather.WeatherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/dailyweather")
    public String getWeatherDailyInfo(WeatherRequest weatherRequest, Model model) {
        List<TemperatureDailyInfo> data = weatherService.getWeatherDailyData(weatherRequest).getData();
        // todo сделать из этого графики
        model.addAttribute("data", data);
        return "weather-info";
    }

}
