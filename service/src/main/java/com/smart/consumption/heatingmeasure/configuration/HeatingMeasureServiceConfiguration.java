package com.smart.consumption.heatingmeasure.configuration;

import com.smart.consumption.heatingmeasure.DefaultHeatingMeasureService;
import com.smart.consumption.heatingmeasure.FailSafeHeatingMeasureService;
import com.smart.consumption.heatingmeasure.HeatingMeasureService;
import com.smart.consumption.repository.HeatingMeasureEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeatingMeasureServiceConfiguration {
    @Autowired
    private HeatingMeasureEntityRepository heatingMeasureEntityRepository;

    @Bean
    HeatingMeasureService heatingMeasureService(final HeatingMeasureService defaultHeatingMeasureService) {
        return new FailSafeHeatingMeasureService(defaultHeatingMeasureService);
    }

    @Bean
    HeatingMeasureService defaultHeatingMeasureService() {
        return new DefaultHeatingMeasureService(heatingMeasureEntityRepository);
    }
}
