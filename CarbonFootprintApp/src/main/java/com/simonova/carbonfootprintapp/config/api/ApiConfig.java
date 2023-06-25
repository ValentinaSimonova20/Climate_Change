package com.simonova.carbonfootprintapp.config.api;

import com.simonova.carbonfootprintapp.integration.api.CarbonFootprintApi;
import com.simonova.carbonfootprintapp.model.CarbonFootprintProperties;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ApiConfig {

    private final CarbonFootprintProperties carbonFootprintProperties;

    @Bean
    public CarbonFootprintApi newsApi() {
        return Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .target(CarbonFootprintApi.class, carbonFootprintProperties.getFootprint().get("baseurl"));
    }
}
