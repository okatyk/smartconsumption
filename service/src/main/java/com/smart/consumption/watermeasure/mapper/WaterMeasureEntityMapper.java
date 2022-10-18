package com.smart.consumption.watermeasure.mapper;

import com.smart.consumption.ModelMapper;
import com.smart.consumption.entity.WaterMeasureEntity;
import com.smart.consumption.watermeasure.model.WaterMeasureModel;

public class WaterMeasureEntityMapper implements ModelMapper<WaterMeasureModel, WaterMeasureEntity> {

    @Override
    public WaterMeasureModel mapEntityToModel(final WaterMeasureEntity waterMeasureEntity) {
        return WaterMeasureModel.builder()
            .id(waterMeasureEntity.getId())
            .measuring(waterMeasureEntity.getMeasuring())
            .unitOfMeasure(waterMeasureEntity.getUnitOfMeasure())
            .measuredData(waterMeasureEntity.getMeasuredData())
            .dateOfMeasuring(waterMeasureEntity.getDateOfMeasuring())
            .build();
    }

    @Override
    public WaterMeasureEntity mapModelToEntity(final WaterMeasureModel waterMeasureModel) {
        WaterMeasureEntity waterMeasureEntity = new WaterMeasureEntity();
        waterMeasureEntity.setMeasuring(waterMeasureModel.getMeasuring());
        waterMeasureEntity.setUnitOfMeasure(waterMeasureModel.getUnitOfMeasure());
        waterMeasureEntity.setMeasuredData(waterMeasureModel.getMeasuredData());
        waterMeasureEntity.setDateOfMeasuring(waterMeasureModel.getDateOfMeasuring());
        return waterMeasureEntity;
    }
}
