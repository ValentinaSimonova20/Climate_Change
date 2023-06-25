package com.simonova.carbonfootprintapp.controller;

import com.simonova.carbonfootprintapp.model.EmissionsData;
import com.simonova.carbonfootprintapp.service.CarbonFootprintService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carbonFootprint")
@RequiredArgsConstructor
@Slf4j
public class CarbonFootprintController {

    private final CarbonFootprintService carbonFootprintService;

    @GetMapping
    public @ResponseBody EmissionsData getEmissionsData(String activityId) {
        return carbonFootprintService.getCarbonFootprintInformation(activityId);
    }
}
