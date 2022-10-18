package com.smart.consumption.watermeasure;

import com.smart.consumption.ConsumptionResponse;
import com.smart.consumption.watermeasure.model.WaterMeasureModel;
import java.time.LocalDate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WaterMeasureService {
    Flux<WaterMeasureModel> getAllWaterMeasures();
    Mono<ConsumptionResponse<WaterMeasureModel>> getAllWaterByDate(final LocalDate start, final LocalDate end);
}
