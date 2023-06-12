package com.simonova.carbonfootprintapp.service;

import com.simonova.carbonfootprintapp.integration.api.CarbonFootprintApi;
import com.simonova.carbonfootprintapp.model.Body;
import com.simonova.carbonfootprintapp.model.BodyEmissionFactor;
import com.simonova.carbonfootprintapp.model.EmissionsData;
import com.simonova.carbonfootprintapp.model.MoneyParameters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class CarbonFootprintServiceImpl implements CarbonFootprintService {

    CarbonFootprintApi carbonFootprintApi;
    private final String apiKey;
    private final String dataVersion;

    public CarbonFootprintServiceImpl(@Value("${carbon.footprint.api.key}")String apiKey,  @Value("${carbon.footprint.data.version}")String dataVersion) {
        this.apiKey = apiKey;
        this.dataVersion = dataVersion;
    }

    @Override
    public EmissionsData getCarbonFootprintInformation(String activityId) {
        Body body = new Body();
        BodyEmissionFactor bodyEmissionFactor = new BodyEmissionFactor();
        bodyEmissionFactor.activityId(activityId);
        bodyEmissionFactor.dataVersion(dataVersion);
        body.emissionFactor(bodyEmissionFactor);
        MoneyParameters moneyParameters = new MoneyParameters();
        moneyParameters.setMoney(new BigDecimal(500));
        moneyParameters.setMoneyUnit("usd");
        body.parameters(moneyParameters);
        return carbonFootprintApi.getEstimatedDate(body, apiKey);
    }

}
