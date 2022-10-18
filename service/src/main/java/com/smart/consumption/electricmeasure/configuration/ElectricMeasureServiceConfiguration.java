package com.smart.consumption.electricmeasure.configuration;

import com.smart.consumption.electricmeasure.DefaultElectricMeasureService;
import com.smart.consumption.electricmeasure.ElectricMeasureService;
import com.smart.consumption.electricmeasure.FailSafeElectricMeasureService;
import com.smart.consumption.repository.ElectricMeasureEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElectricMeasureServiceConfiguration {

    @Autowired
    private ElectricMeasureEntityRepository electricMeasureEntityRepository;

    @Bean
     ElectricMeasureService electricMeasureService(final ElectricMeasureService defaultElectricMeasureService) {
        return new FailSafeElectricMeasureService(defaultElectricMeasureService);
    }

    @Bean
    ElectricMeasureService defaultElectricMeasureService() {
        return new DefaultElectricMeasureService(electricMeasureEntityRepository);
    }
}
