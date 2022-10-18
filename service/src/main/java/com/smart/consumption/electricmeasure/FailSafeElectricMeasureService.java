package com.smart.consumption.electricmeasure;

import com.smart.consumption.ConsumptionResponse;
import com.smart.consumption.electricmeasure.model.ElectricMeasureModel;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class FailSafeElectricMeasureService implements ElectricMeasureService {
    private final ElectricMeasureService electricMeasureService;

    @Override
    public Flux<ElectricMeasureModel> getAllElectricMeasures() {
        return electricMeasureService.getAllElectricMeasures()
            .doOnError(error -> log.error("Error, can't get datas for electric! ", error))
            .onErrorResume(error -> Flux.error(new Exception("Error, can't get datas for electric! ")));
    }


    @Override
    public Mono<ConsumptionResponse<ElectricMeasureModel>> getAllElectricByDate(final LocalDate start, final LocalDate end) {
        return electricMeasureService.getAllElectricByDate(start, end)
            .doOnError(error -> log.error("Error, can't get datas by date for electric! ", error))
            .onErrorResume(error -> Mono.empty());
    }
}
