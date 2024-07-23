package com.simonova.ecoinformerapp.controllers;

import com.simonova.ecoinformerapp.model.WeatherDailyData;
import com.simonova.ecoinformerapp.model.WeatherRequest;
import com.simonova.ecoinformerapp.services.weather.WeatherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@Slf4j
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/dailyweather")
    public String getWeatherDailyInfo(WeatherRequest weatherRequest, @RequestParam(value = "month", required = false, defaultValue = "JANUARY") Month month, Model model) {

        WeatherDailyData weatherDailyData = weatherService.getWeatherDailyData(weatherRequest);
        List<List<Double>> data = weatherService
                .getWeatherDailyDataByMonth(weatherDailyData, month)
                .stream().map(doubleArray -> Arrays.stream(doubleArray).collect(Collectors.toList())).collect(Collectors.toList());
        model.addAttribute("data", data);
        model.addAttribute("headers", Arrays.asList(weatherService.getHeaders(weatherDailyData)));
        return "weather-info";
    }

}
