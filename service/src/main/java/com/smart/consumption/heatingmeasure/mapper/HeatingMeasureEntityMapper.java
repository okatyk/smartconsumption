package com.smart.consumption.heatingmeasure.mapper;

import com.smart.consumption.ModelMapper;
import com.smart.consumption.entity.HeatingMeasureEntity;
import com.smart.consumption.heatingmeasure.model.HeatingMeasureModel;

public class HeatingMeasureEntityMapper implements ModelMapper<HeatingMeasureModel, HeatingMeasureEntity> {

    @Override
    public HeatingMeasureModel mapEntityToModel(final HeatingMeasureEntity heatingMeasureEntity) {
        return HeatingMeasureModel.builder()
            .id(heatingMeasureEntity.getId())
            .measuring(heatingMeasureEntity.getMeasuring())
            .unitOfMeasure(heatingMeasureEntity.getUnitOfMeasure())
            .measuredData(heatingMeasureEntity.getMeasuredData())
            .dateOfMeasuring(heatingMeasureEntity.getDateOfMeasuring())
            .build();
    }

    @Override
    public HeatingMeasureEntity mapModelToEntity(final HeatingMeasureModel heatingMeasureModel) {
        HeatingMeasureEntity heatingMeasureEntity = new HeatingMeasureEntity();
        heatingMeasureEntity.setMeasuring(heatingMeasureModel.getMeasuring());
        heatingMeasureEntity.setUnitOfMeasure(heatingMeasureModel.getUnitOfMeasure());
        heatingMeasureEntity.setMeasuredData(heatingMeasureModel.getMeasuredData());
        heatingMeasureEntity.setDateOfMeasuring(heatingMeasureModel.getDateOfMeasuring());
        return heatingMeasureEntity;
    }
}
