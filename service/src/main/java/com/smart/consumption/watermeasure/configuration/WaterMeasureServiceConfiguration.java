package com.smart.consumption.watermeasure.configuration;

import com.smart.consumption.repository.WaterMeasureEntityRepository;
import com.smart.consumption.watermeasure.DefaultWaterMeasureService;
import com.smart.consumption.watermeasure.FailSafeWaterMeasureService;
import com.smart.consumption.watermeasure.WaterMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WaterMeasureServiceConfiguration {
    @Autowired
    private WaterMeasureEntityRepository waterMeasureEntityRepository;

    @Bean
    WaterMeasureService waterMeasureService(final WaterMeasureService defaultWaterMeasureService) {
        return new FailSafeWaterMeasureService(defaultWaterMeasureService);
    }

    @Bean
    WaterMeasureService defaultWaterMeasureService() {
        return new DefaultWaterMeasureService(waterMeasureEntityRepository);
    }

}
