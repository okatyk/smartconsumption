package com.smart.consumption.electricmeasure;

import com.smart.consumption.ConsumptionResponse;
import com.smart.consumption.electricmeasure.model.ElectricMeasureModel;
import java.time.LocalDate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ElectricMeasureService {

    Flux<ElectricMeasureModel> getAllElectricMeasures();

    Mono<ConsumptionResponse<ElectricMeasureModel>> getAllElectricByDate(final LocalDate start, final LocalDate end);
}
