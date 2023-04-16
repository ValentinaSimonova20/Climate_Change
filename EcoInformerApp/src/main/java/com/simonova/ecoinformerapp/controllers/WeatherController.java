package com.simonova.ecoinformerapp.controllers;

import com.simonova.ecoinformerapp.model.WeatherDailyData;
import com.simonova.ecoinformerapp.model.WeatherRequest;
import com.simonova.ecoinformerapp.model.WeatherSeasonData;
import com.simonova.ecoinformerapp.services.weather.WeatherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
@Slf4j
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
        log.info("Weather request: " + weatherRequest);
        model.addAttribute("seasons", weatherService.getAllSeasonNames());
        model.addAttribute("tempNames", weatherService.getAllTempNames());
        model.addAttribute("city", weatherRequest.getCity());
        model.addAttribute("startDate", weatherRequest.getStartDate());
        model.addAttribute("endDate", weatherRequest.getEndDate());
        model.addAttribute(
                "weatherDict",
                weatherService.getSeasonInfoForUi(weatherService.getWeatherSeasonData(weatherRequest))
        );
        return "weather-info";
    }
}
