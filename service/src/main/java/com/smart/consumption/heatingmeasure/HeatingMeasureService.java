package com.smart.consumption.heatingmeasure;

import com.smart.consumption.ConsumptionResponse;
import com.smart.consumption.heatingmeasure.model.HeatingMeasureModel;
import java.time.LocalDate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface HeatingMeasureService {
    Flux<HeatingMeasureModel> getAllHeatingMeasures();
    Mono<ConsumptionResponse<HeatingMeasureModel>> getAllHeatingByDate(final LocalDate start, final LocalDate end);
}
