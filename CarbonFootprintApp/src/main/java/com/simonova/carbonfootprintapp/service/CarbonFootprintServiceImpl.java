package com.simonova.carbonfootprintapp.service;

import com.simonova.carbonfootprintapp.integration.api.CarbonFootprintApi;
import com.simonova.carbonfootprintapp.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarbonFootprintServiceImpl implements CarbonFootprintService {

    private final CarbonFootprintApi carbonFootprintApi;
    private final ActivityTypeProperties activityTypeProperties;
    private final CarbonFootprintProperties carbonFootprintProperties;

    @Override
    public EmissionsData getCarbonFootprintInformation(String activityId) {
        Body body = new Body();
        BodyEmissionFactor bodyEmissionFactor = new BodyEmissionFactor();
        bodyEmissionFactor.activityId(activityTypeProperties.getType().get(activityId));
        bodyEmissionFactor.dataVersion(carbonFootprintProperties.getFootprint().get("data.version"));
        body.emissionFactor(bodyEmissionFactor);
        MoneyParameters moneyParameters = new MoneyParameters();
        moneyParameters.setMoney(new BigDecimal(500));
        moneyParameters.setMoneyUnit("usd");
        body.parameters(moneyParameters);
        return carbonFootprintApi.getEstimatedDate(body,"Bearer " + carbonFootprintProperties.getFootprint().get("api.key"));
    }

}
