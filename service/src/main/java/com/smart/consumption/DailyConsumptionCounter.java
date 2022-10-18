package com.smart.consumption;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DailyConsumptionCounter<MeasureType extends MeasureModel> {

    public List<Consumptions>   dailyConsumptionNonGrouped(final List<MeasureType> measuresList) {
        List<Consumptions> dailyConsumptionList = new ArrayList<>();
        Map<LocalDate, List<MeasureType>> groupedByLocalDate = measuresList.stream()
            .collect(Collectors.groupingBy(date -> date.getDateOfMeasuring().toLocalDate()));

        for (Map.Entry<LocalDate, List<MeasureType>> entry : groupedByLocalDate.entrySet()) {
            var groupedByMeters = entry.getValue().stream().collect(Collectors.groupingBy(MeasureModel::getMeasuring));

            for (Map.Entry<String, List<MeasureType>> listEntry : groupedByMeters.entrySet()) {

                var lastDaily = listEntry.getValue().stream()
                    .max(Comparator.comparing(MeasureModel::getMeasuredData));
                var firstDaily = listEntry.getValue().stream()
                    .min(Comparator.comparing(MeasureModel::getMeasuredData));

                if (firstDaily.isPresent()) {
                    var daily = new Consumptions(listEntry.getKey(),
                        lastDaily.get().getMeasuredData() - firstDaily.get().getMeasuredData(), entry.getKey());
                    dailyConsumptionList.add(daily);
                }
            }
        }
        return dailyConsumptionList;
    }
}
