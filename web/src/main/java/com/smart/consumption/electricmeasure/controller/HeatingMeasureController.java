package com.smart.consumption.electricmeasure.controller;

import com.smart.consumption.ConsumptionResponse;
import com.smart.consumption.heatingmeasure.HeatingMeasureService;
import com.smart.consumption.heatingmeasure.model.HeatingMeasureModel;

import java.time.LocalDate;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class HeatingMeasureController {
    @Autowired
    private final HeatingMeasureService heatingMeasureService;

    @GetMapping("/getAllHeating")
    public Flux<HeatingMeasureModel> getAllHeatingMeasures() {
        return heatingMeasureService.getAllHeatingMeasures();
    }

    @GetMapping("/getHeatingByDate")
    public Mono<ConsumptionResponse<HeatingMeasureModel>> getHeatingByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate start,
                                                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate end) {
        return heatingMeasureService.getAllHeatingByDate(start, end);

    }
}
