package com.smart.consumption.electricmeasure.mapper;

import com.smart.consumption.ModelMapper;
import com.smart.consumption.electricmeasure.model.ElectricMeasureModel;
import com.smart.consumption.entity.ElectricMeasureEntity;

public class ElectricMeasureEntityMapper implements ModelMapper<ElectricMeasureModel, ElectricMeasureEntity> {

    @Override
    public ElectricMeasureModel mapEntityToModel(final ElectricMeasureEntity electricMeasureEntity) {
        return ElectricMeasureModel.builder()
            .measuring(electricMeasureEntity.getMeasuring())
            .unitOfMeasure(electricMeasureEntity.getUnitOfMeasure())
            .measuredData(electricMeasureEntity.getMeasuredData())
            .dateOfMeasuring(electricMeasureEntity.getDateOfMeasuring())
            .build();
    }

    @Override
    public ElectricMeasureEntity mapModelToEntity(final ElectricMeasureModel electricMeasureModel) {
        ElectricMeasureEntity electricMeasureEntity = new ElectricMeasureEntity();
        electricMeasureEntity.setMeasuring(electricMeasureModel.getMeasuring());
        electricMeasureEntity.setUnitOfMeasure(electricMeasureModel.getUnitOfMeasure());
        electricMeasureEntity.setMeasuredData(electricMeasureModel.getMeasuredData());
        electricMeasureEntity.setDateOfMeasuring(electricMeasureModel.getDateOfMeasuring());
        return electricMeasureEntity;
    }
}
