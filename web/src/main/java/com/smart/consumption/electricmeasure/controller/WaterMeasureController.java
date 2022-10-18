package com.smart.consumption.electricmeasure.controller;

import com.smart.consumption.ConsumptionResponse;
import com.smart.consumption.watermeasure.WaterMeasureService;
import com.smart.consumption.watermeasure.model.WaterMeasureModel;

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
public class WaterMeasureController {
    @Autowired
    private final WaterMeasureService waterMeasureService;

    @GetMapping("/getAllWater")
    public Flux<WaterMeasureModel> getAllWaterMeasures() {
        return waterMeasureService.getAllWaterMeasures();
    }

    @GetMapping("/getWaterByDate")
    public Mono<ConsumptionResponse<WaterMeasureModel>> getWaterByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate start,
                                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate end) {
        return waterMeasureService.getAllWaterByDate(start, end);

    }
}
