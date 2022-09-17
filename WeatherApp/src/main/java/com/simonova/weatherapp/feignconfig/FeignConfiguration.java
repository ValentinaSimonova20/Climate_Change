package com.simonova.weatherapp.feignconfig;

import com.simonova.weatherapp.integration.api.LocationApi;
import com.simonova.weatherapp.integration.api.WeatherApi;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Value("${location.api.baseUrl}")
    private String locationApiHost;
    @Value("${weather.api.baseUrl}")
    private String weatherApiHost;

    @Bean
    public LocationApi feignLocationApi()  {
        return getFeignApi(LocationApi.class, locationApiHost);
    }

    @Bean
    public WeatherApi feignWeatherApi() {
        return getFeignApi(WeatherApi.class, weatherApiHost);
    }

    private <T> T getFeignApi(Class<T> apiClass, String host) {
        return Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .target(apiClass, host);
    }
}
