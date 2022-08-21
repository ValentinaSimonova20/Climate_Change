package com.simonova.ecoinformerapp.configuration;

import com.simonova.ecoinformerapp.integration.api.WeatherApi;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.apache.commons.codec.StringEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeatherApiConfiguration {

    @Value("${weather.api.host}")
    private String weatherApiHost;

    @Bean
    public WeatherApi feignWeatherApi()  {
        return Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .target(WeatherApi.class, weatherApiHost);
    }
}
