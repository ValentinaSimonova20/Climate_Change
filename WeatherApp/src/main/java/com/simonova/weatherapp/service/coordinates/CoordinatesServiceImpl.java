package com.simonova.weatherapp.service.coordinates;

import com.simonova.weatherapp.integration.api.LocationApi;
import com.simonova.weatherapp.model.LocationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoordinatesServiceImpl implements CoordinatesService {
    private final LocationApi getLocationClient;
    @Value("${location.api.key}")
    private String locationApiKey;

    @Override
    public LocationInfo getCoordinatesByAddress(String address) {
        return getLocationClient.getLocationData(address, locationApiKey).getData().get(0);
    }
}
