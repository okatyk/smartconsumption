package com.smart.consumption;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication( scanBasePackages = "com.smart.consumption")
@EnableJpaRepositories(basePackages = "com.smart.consumption")
@EntityScan(basePackages = "com.smart.consumption")
@PropertySource("classpath:application-test.yml")
public class SmartConsumptionApplication {
    public static void main(final String[] args) {
        SpringApplication.run(SmartConsumptionApplication.class, args);
    }
}

