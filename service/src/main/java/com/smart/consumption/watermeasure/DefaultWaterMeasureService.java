package com.smart.consumption.watermeasure;

import com.smart.consumption.ConsumptionResponse;
import com.smart.consumption.ModelMapper;
import com.smart.consumption.Consumptions;
import com.smart.consumption.entity.WaterMeasureEntity;
import com.smart.consumption.repository.WaterMeasureEntityRepository;
import com.smart.consumption.watermeasure.mapper.WaterMeasureEntityMapper;
import com.smart.consumption.watermeasure.model.WaterMeasureModel;

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
public class DefaultWaterMeasureService implements WaterMeasureService {

    @NonNull
    private final WaterMeasureEntityRepository waterMeasureEntityRepository;
    @Setter(AccessLevel.PACKAGE)
    private ModelMapper<WaterMeasureModel, WaterMeasureEntity> entityMapper = new WaterMeasureEntityMapper();

    @Override
    public Flux<WaterMeasureModel> getAllWaterMeasures() {
        return Flux.fromStream(waterMeasureEntityRepository.findAll().stream()
                .map(waterMeasureEntity -> entityMapper.mapEntityToModel(waterMeasureEntity)));
    }

    @Override
    public Mono<ConsumptionResponse<WaterMeasureModel>> getAllWaterByDate(final LocalDate start, final LocalDate end) {
        final LocalDateTime startOfTheDay = start.atStartOfDay();
        final LocalDateTime endOfTheDay = end.plusDays(1).atStartOfDay();

        final List<WaterMeasureModel> collect = waterMeasureEntityRepository.findAllByDateOfMeasuringBetween(startOfTheDay, endOfTheDay).stream()
                .map(waterMeasureEntity -> entityMapper.mapEntityToModel(waterMeasureEntity))
                .collect(Collectors.toList());

        var min = collect.stream()
                .min(Comparator.comparing(WaterMeasureModel::getMeasuredData))
                .orElse(WaterMeasureModel.builder().measuredData(0).build());
        var max = collect.stream()
                .max(Comparator.comparing(WaterMeasureModel::getMeasuredData))
                .orElse(WaterMeasureModel.builder().measuredData(100000).build());

        Map<LocalDate, List<WaterMeasureModel>> byDates = collect.stream()
                .collect(Collectors.groupingBy(date -> date.getDateOfMeasuring().toLocalDate()));

        final List<Consumptions> dailyConsumption = new ArrayList<>();

        for (Map.Entry<LocalDate, List<WaterMeasureModel>> entry : byDates.entrySet()) {
            var grouped = entry.getValue().stream().collect(Collectors.groupingBy(WaterMeasureModel::getMeasuring));
            for (Map.Entry<String, List<WaterMeasureModel>> listEntry : grouped.entrySet()) {
                var lastDaily = listEntry.getValue().stream()
                        .max(Comparator.comparing(WaterMeasureModel::getMeasuredData))
                        .orElse(WaterMeasureModel.builder().build());

                var firstDaily = listEntry.getValue().stream()
                        .min(Comparator.comparing(WaterMeasureModel::getMeasuredData))
                        .orElse(WaterMeasureModel.builder().build());

                var daily = new Consumptions(listEntry.getKey(), lastDaily.getMeasuredData() - firstDaily.getMeasuredData(), entry.getKey());
                dailyConsumption.add(daily);
            }


        }
        var response = new ConsumptionResponse<>(collect, new ArrayList<>(), dailyConsumption, min.getMeasuredData(), max.getMeasuredData());

        response.getConsumptionList().stream()
                .collect(Collectors.groupingBy(WaterMeasureModel::getMeasuring, Collectors.maxBy(Comparator.comparing(WaterMeasureModel::getDateOfMeasuring))))
                .forEach((id, sumTargetCost) -> response.getConsumptionsList()
                        .add(Consumptions.builder().group(id).value(sumTargetCost.map(WaterMeasureModel::getMeasuredData).orElse(0.0)).build()));

        return Mono.just(response);
    }
}
