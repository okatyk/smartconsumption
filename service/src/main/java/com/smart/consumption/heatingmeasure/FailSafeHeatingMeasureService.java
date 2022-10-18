package com.smart.consumption.heatingmeasure;

import com.smart.consumption.ConsumptionResponse;
import com.smart.consumption.heatingmeasure.model.HeatingMeasureModel;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class FailSafeHeatingMeasureService implements HeatingMeasureService {
    private final HeatingMeasureService heatingMeasureService;

    @Override
    public Flux<HeatingMeasureModel> getAllHeatingMeasures() {
        return heatingMeasureService.getAllHeatingMeasures()
            .doOnError(error -> log.error("Error, can't get datas for heating! ", error))
            .onErrorResume(error -> Flux.empty());
    }


    @Override
    public Mono<ConsumptionResponse<HeatingMeasureModel>> getAllHeatingByDate(final LocalDate start, final LocalDate end) {
        return heatingMeasureService.getAllHeatingByDate(start, end)
            .doOnError(error -> log.error("Error, can't get datas by date for heating! ", error))
            .onErrorResume(error -> Mono.empty());
    }
}

