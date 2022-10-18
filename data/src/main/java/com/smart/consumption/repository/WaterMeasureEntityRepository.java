package com.smart.consumption.repository;

import com.smart.consumption.entity.WaterMeasureEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface WaterMeasureEntityRepository extends CrudRepository<WaterMeasureEntity, Long> {
    List<WaterMeasureEntity> findAll();
    List<WaterMeasureEntity> findAllByDateOfMeasuringBetween(LocalDateTime start, LocalDateTime end);
    WaterMeasureEntity save(WaterMeasureEntity entity);

}
