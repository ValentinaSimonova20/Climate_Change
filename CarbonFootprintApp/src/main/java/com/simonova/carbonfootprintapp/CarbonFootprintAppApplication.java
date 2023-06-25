package com.simonova.carbonfootprintapp;

import com.simonova.carbonfootprintapp.model.ActivityTypeProperties;
import com.simonova.carbonfootprintapp.model.CarbonFootprintProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ActivityTypeProperties.class, CarbonFootprintProperties.class})
public class CarbonFootprintAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarbonFootprintAppApplication.class, args);
    }

}
