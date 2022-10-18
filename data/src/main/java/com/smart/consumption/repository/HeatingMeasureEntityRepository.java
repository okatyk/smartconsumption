package com.smart.consumption.repository;

import com.smart.consumption.entity.HeatingMeasureEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface HeatingMeasureEntityRepository extends CrudRepository<HeatingMeasureEntity, Long> {
    List<HeatingMeasureEntity> findAll();
    List<HeatingMeasureEntity> findAllByDateOfMeasuringBetween(LocalDateTime start, LocalDateTime end);
    HeatingMeasureEntity save(HeatingMeasureEntity entity);
}
