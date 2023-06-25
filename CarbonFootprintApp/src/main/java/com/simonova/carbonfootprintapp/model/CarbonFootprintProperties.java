package com.simonova.carbonfootprintapp.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties("carbon")
public class CarbonFootprintProperties {
    private Map<String, String> footprint;

    public Map<String, String> getFootprint() {
        return footprint;
    }

    public void setFootprint(Map<String, String> footprint) {
        this.footprint = footprint;
    }
}
