package com.smart.consumption.electricmeasure;


import com.smart.consumption.ConsumptionResponse;
import com.smart.consumption.Consumptions;
import com.smart.consumption.DailyConsumptionCounter;
import com.smart.consumption.ModelMapper;
import com.smart.consumption.electricmeasure.mapper.ElectricMeasureEntityMapper;
import com.smart.consumption.electricmeasure.model.ElectricMeasureModel;
import com.smart.consumption.entity.ElectricMeasureEntity;
import com.smart.consumption.repository.ElectricMeasureEntityRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DefaultElectricMeasureService implements ElectricMeasureService {

    @NonNull
    private final ElectricMeasureEntityRepository electricMeasureEntityRepository;
    @Setter(AccessLevel.PACKAGE)
    private ModelMapper<ElectricMeasureModel, ElectricMeasureEntity> entityMapper = new ElectricMeasureEntityMapper();
    @Setter(AccessLevel.PACKAGE)
    private DailyConsumptionCounter<ElectricMeasureModel> dailyConsumptionCounter = new DailyConsumptionCounter<>();

    @Override
    public Flux<ElectricMeasureModel> getAllElectricMeasures() {
        return Flux.fromStream(electricMeasureEntityRepository.findAll().stream()
                .map(electricMeasureEntity -> entityMapper.mapEntityToModel(electricMeasureEntity)));

    }

    @Override
    public Mono<ConsumptionResponse<ElectricMeasureModel>> getAllElectricByDate(final LocalDate start, final LocalDate end) {
        final LocalDateTime startOfTheDay = start.atStartOfDay();
        final LocalDateTime endOfTheDay = end.plusDays(1).atStartOfDay();

        final List<ElectricMeasureModel> electricMeasureModelList = electricMeasureEntityRepository.findAllByDateOfMeasuringBetween(startOfTheDay, endOfTheDay).stream()
                .map(electricMeasureEntity -> entityMapper.mapEntityToModel(electricMeasureEntity))
                .collect(Collectors.toList());

        var min = electricMeasureModelList.stream()
                .min(Comparator.comparing(ElectricMeasureModel::getMeasuredData))
                .orElse(ElectricMeasureModel.builder().measuredData(0).build());

        var max = electricMeasureModelList.stream()
                .max(Comparator.comparing(ElectricMeasureModel::getMeasuredData))
                .orElse(ElectricMeasureModel.builder().measuredData(100000).build());

        final List<Consumptions> dailyConsumption = dailyConsumptionCounter.dailyConsumptionNonGrouped(electricMeasureModelList);

        var response = new ConsumptionResponse<>(electricMeasureModelList, new ArrayList<>(), dailyConsumption, min.getMeasuredData(), max.getMeasuredData());

        response.getConsumptionList().stream()
                .collect(Collectors.groupingBy(ElectricMeasureModel::getMeasuring, Collectors.maxBy(Comparator.comparing(ElectricMeasureModel::getDateOfMeasuring))))
                .forEach((id, sumTargetCost) -> response.getConsumptionsList()
                        .add(Consumptions.builder().group(id).value(sumTargetCost.map(ElectricMeasureModel::getMeasuredData).orElse(0.0)).build()));

        return Mono.just(response);
    }
}