package com.simonova.carbonfootprintapp.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties("activity")
public class ActivityTypeProperties {
    private Map<String, String> type;

    public Map<String, String> getType() {
        return type;
    }

    public void setType(Map<String, String> type) {
        this.type = type;
    }
}
