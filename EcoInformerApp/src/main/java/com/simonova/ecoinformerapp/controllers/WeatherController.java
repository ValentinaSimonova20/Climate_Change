package com.simonova.ecoinformerapp.controllers;

import com.simonova.ecoinformerapp.model.WeatherDailyData;
import com.simonova.ecoinformerapp.model.WeatherRequest;
import com.simonova.ecoinformerapp.model.WeatherSeasonData;
import com.simonova.ecoinformerapp.services.weather.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/dailyweather")
    public @ResponseBody WeatherDailyData getWeatherDailyInfo(WeatherRequest weatherRequest) {
        return weatherService.getWeatherDailyData(weatherRequest);
    }

    @GetMapping("/seasonweather")
    public @ResponseBody WeatherSeasonData getWeatherSeasonInfo(WeatherRequest weatherRequest) {
        return weatherService.getWeatherSeasonData(weatherRequest);
    }

    @GetMapping("/climateChange/getSeasonWeatherInfo")
    public String getSeasonWeatherInfoPage(WeatherRequest weatherRequest, Model model) {
        model.addAttribute("seasons", weatherService.getAllSeasonNames());
        model.addAttribute("tempNames", weatherService.getAllTempNames());
        model.addAttribute(
                "weatherDict",
                weatherService.getSeasonInfoForUi(weatherService.getWeatherSeasonData(weatherRequest))
        );
        return "weather-info";
    }
}
