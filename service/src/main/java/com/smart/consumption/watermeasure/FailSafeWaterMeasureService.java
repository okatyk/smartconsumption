package com.smart.consumption.watermeasure;

import com.smart.consumption.ConsumptionResponse;
import com.smart.consumption.watermeasure.model.WaterMeasureModel;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class FailSafeWaterMeasureService implements WaterMeasureService {
    private final WaterMeasureService waterMeasureService;

    @Override
    public Flux<WaterMeasureModel> getAllWaterMeasures() {
        return waterMeasureService.getAllWaterMeasures()
            .doOnError(error -> log.error("Error, can't get datas for water! ", error))
            .onErrorResume(error -> Flux.empty());
    }


    @Override
    public Mono<ConsumptionResponse<WaterMeasureModel>> getAllWaterByDate(final LocalDate start, final LocalDate end) {
        return waterMeasureService.getAllWaterByDate(start, end)
            .doOnError(error -> log.error("Error, can't get datas by date for water! ", error))
            .onErrorResume(error -> Mono.empty());
    }
}
