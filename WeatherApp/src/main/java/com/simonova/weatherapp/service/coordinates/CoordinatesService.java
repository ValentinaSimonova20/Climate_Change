package com.simonova.weatherapp.service.coordinates;


import com.simonova.weatherapp.model.LocationInfo;

public interface CoordinatesService {
    LocationInfo getCoordinatesByAddress(String address);
}
