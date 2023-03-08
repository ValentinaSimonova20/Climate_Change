package com.climatechange.newsapp.feignconfig;

import com.climatechange.newsapp.integration.api.NewsApi;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Value("${news.api.baseUrl}")
    private String newsApiHost;

    @Bean
    public NewsApi newsApi() {
        return Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .target(NewsApi.class, newsApiHost);
    }
}
