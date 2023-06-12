package com.simonova.carbonfootprintapp.service;

import com.simonova.carbonfootprintapp.model.EmissionsData;

/**
 * Get carbon footprint information
 */
public interface CarbonFootprintService {
    EmissionsData getCarbonFootprintInformation(String activityId);
}
