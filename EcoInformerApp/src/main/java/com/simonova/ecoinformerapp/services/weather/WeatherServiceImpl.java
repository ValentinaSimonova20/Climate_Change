package com.simonova.ecoinformerapp.services.weather;

import com.simonova.ecoinformerapp.integration.api.WeatherApi;
import com.simonova.ecoinformerapp.model.WeatherDailyData;
import com.simonova.ecoinformerapp.model.WeatherRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
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
    public List<Double[]> getJanuaryWeatherDailyData(WeatherDailyData weatherDailyData) {
        List<double[]> result = new ArrayList<>();
        String[] headers = getHeaders(weatherDailyData);
        // заполнение таблицы первональными значениями чтобы обеспечить одинаковое число элементов в каждом списке
        fillTable(result, headers.length);
        weatherDailyData
                .getData()
                .stream()
                .filter(info -> info.getMonth() == 1)
                .forEach(info -> {
                    int rowIndex = info.getDay() - 1;
                    int columnIndex = Arrays.asList(headers).indexOf(info.getYear().toString());
                    result.get(rowIndex)[columnIndex] = Double.parseDouble(info.getTmax());
                });
        return result.stream().map(ArrayUtils::toObject).collect(Collectors.toList());
    }

    public String[] getHeaders(WeatherDailyData weatherDailyData) {
        return weatherDailyData
                        .getData()
                        .stream()
                        .filter(info -> info.getMonth() == 1)
                        .map(info -> info.getYear().toString()
                        ).distinct().sorted().toArray(String[]::new);
    }

    private void fillTable(List<double[]> result, int capacity) {
        for(int i = 0; i < 31; i++) {
            result.add(new double[capacity]);
        }
    }
}
