package com.simonova.ecoinformerapp.services.weather;

import com.simonova.ecoinformerapp.integration.api.WeatherApi;
import com.simonova.ecoinformerapp.model.WeatherDailyData;
import com.simonova.ecoinformerapp.model.WeatherRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherApi weatherApi;

    @Override
    public WeatherDailyData getWeatherDailyData(WeatherRequest weatherRequest) {
        return weatherApi.getDailyWeatherInfo(
                weatherRequest.getCity(),
                weatherRequest.getStartDate(),
                weatherRequest.getEndDate()
        );
    }

    @Override
    public List<List<Object>> getJanuaryWeatherDailyData(WeatherDailyData weatherDailyData) {
        List<List<Object>> result = new ArrayList<>();
        List<Object> headers = new ArrayList<>(getHeaders(weatherDailyData));
        headers.add(0, "dates");
        result.add(headers);
        fillTable(result, headers.size());
        weatherDailyData
                .getData()
                .stream()
                .filter(info -> info.getDate().contains("-01-"))
                .forEach(info -> {
                    int rowIndex = Integer.parseInt(info.getDate().split("-")[2]);
                    int columnIndex = headers.indexOf(info.getDate().split("-")[0]);
                    result.get(rowIndex).set(columnIndex, Double.valueOf(info.getTmax()));
                });
        result.remove(0);
        return result;
    }

    private List<String> getHeaders(WeatherDailyData weatherDailyData) {
        return weatherDailyData
                .getData()
                .stream()
                .filter(info -> info.getDate().contains("-01-"))
                .map(info -> info.getDate().split("-")[0]
                ).distinct().sorted().collect(Collectors.toList());
    }

    private void fillTable(List<List<Object>> result, int capacity) {
        for(int i = 0; i < 31; i++) {
            List<Object> row = new ArrayList<>(capacity);
            row.add(0, String.valueOf(i + 1));
            for(int j = 1; j < capacity; j++) {
                row.add(j, 0.0);
            }
            result.add(row);
        }
    }
}
