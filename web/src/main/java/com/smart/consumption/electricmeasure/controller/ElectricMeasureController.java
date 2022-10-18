package com.smart.consumption.electricmeasure.controller;

import com.smart.consumption.ConsumptionResponse;
import com.smart.consumption.electricmeasure.ElectricMeasureService;
import com.smart.consumption.electricmeasure.model.ElectricMeasureModel;

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
public class ElectricMeasureController {
    @Autowired
    private final ElectricMeasureService electricMeasureService;

    @GetMapping("/getAllElectric")
    public Flux<ElectricMeasureModel> getAllElectricMeasures() {
        return electricMeasureService.getAllElectricMeasures();
    }

    @GetMapping("/getElectricByDate")
    public Mono<ConsumptionResponse<ElectricMeasureModel>> getElectricByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate start,
                                                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate end) {
        return electricMeasureService.getAllElectricByDate(start, end);

    }
}
