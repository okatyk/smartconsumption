package com.smart.consumption.heatingmeasure;

import com.smart.consumption.ConsumptionResponse;
import com.smart.consumption.ModelMapper;
import com.smart.consumption.Consumptions;
import com.smart.consumption.entity.HeatingMeasureEntity;
import com.smart.consumption.heatingmeasure.mapper.HeatingMeasureEntityMapper;
import com.smart.consumption.heatingmeasure.model.HeatingMeasureModel;
import com.smart.consumption.repository.HeatingMeasureEntityRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DefaultHeatingMeasureService implements HeatingMeasureService {

    @NonNull
    private final HeatingMeasureEntityRepository heatingMeasureEntityRepository;
    @Setter(AccessLevel.PACKAGE)
    private ModelMapper<HeatingMeasureModel, HeatingMeasureEntity> entityMapper = new HeatingMeasureEntityMapper();

    @Override
    public Flux<HeatingMeasureModel> getAllHeatingMeasures() {
        return Flux.fromStream(heatingMeasureEntityRepository.findAll().stream()
                .map(heatingMeasureEntity -> entityMapper.mapEntityToModel(heatingMeasureEntity)));
    }

    @Override
    public Mono<ConsumptionResponse<HeatingMeasureModel>> getAllHeatingByDate(final LocalDate start, final LocalDate end) {
        final LocalDateTime startOfTheDay = start.atStartOfDay();
        final LocalDateTime endOfTheDay = end.plusDays(1).atStartOfDay();

        final List<HeatingMeasureModel> heatingMeasureModelList = heatingMeasureEntityRepository.findAllByDateOfMeasuringBetween(startOfTheDay, endOfTheDay).stream()
                .map(heatingMeasureEntity -> entityMapper.mapEntityToModel(heatingMeasureEntity))
                .collect(Collectors.toList());

        var min = heatingMeasureModelList.stream()
                .min(Comparator.comparing(HeatingMeasureModel::getMeasuredData))
                .orElse(HeatingMeasureModel.builder().measuredData(0).build());

        var max = heatingMeasureModelList.stream()
                .max(Comparator.comparing(HeatingMeasureModel::getMeasuredData))
                .orElse(HeatingMeasureModel.builder().measuredData(100000).build());

        final Map<LocalDate, List<HeatingMeasureModel>> byDates = heatingMeasureModelList.stream()
                .collect(Collectors.groupingBy(date -> date.getDateOfMeasuring().toLocalDate()));

        final List<Consumptions> dailyConsumption = new ArrayList<>();

        for (Map.Entry<LocalDate, List<HeatingMeasureModel>> entry : byDates.entrySet()) {
            var grouped = entry.getValue().stream().collect(Collectors.groupingBy(HeatingMeasureModel::getMeasuring));
            for (Map.Entry<String, List<HeatingMeasureModel>> listEntry : grouped.entrySet()) {
                var lastDaily = listEntry.getValue().stream()
                        .max(Comparator.comparing(HeatingMeasureModel::getMeasuredData))
                        .orElse(HeatingMeasureModel.builder().build());

                var firstDaily = listEntry.getValue().stream()
                        .min(Comparator.comparing(HeatingMeasureModel::getMeasuredData))
                        .orElse(HeatingMeasureModel.builder().build());

                var daily = new Consumptions(listEntry.getKey(), lastDaily.getMeasuredData() - firstDaily.getMeasuredData(), entry.getKey());
                dailyConsumption.add(daily);
            }

        }
        var response = new ConsumptionResponse<>(heatingMeasureModelList, new ArrayList<>(), dailyConsumption, min.getMeasuredData(), max.getMeasuredData());

        response.getConsumptionList().stream()
                .collect(Collectors.groupingBy(HeatingMeasureModel::getMeasuring, Collectors.maxBy(Comparator.comparing(HeatingMeasureModel::getDateOfMeasuring))))
                .forEach((id, sumTargetCost) -> response.getConsumptionsList()
                        .add(Consumptions.builder().group(id).value(sumTargetCost.map(HeatingMeasureModel::getMeasuredData).orElse(0.0)).build()));
        return Mono.just(response);
    }
}

