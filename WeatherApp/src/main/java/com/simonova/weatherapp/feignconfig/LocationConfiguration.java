package com.simonova.weatherapp.feignconfig;

import com.simonova.weatherapp.integration.api.LocationApi;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocationConfiguration {

    @Value("${location.api.baseUrl}")
    private String locationApiHost;
    @Bean
    public LocationApi feignWeatherApi()  {
        return Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .target(LocationApi.class, locationApiHost);
        }

}
