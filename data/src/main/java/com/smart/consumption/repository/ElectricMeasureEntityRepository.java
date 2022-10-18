package com.smart.consumption.repository;

import com.smart.consumption.entity.ElectricMeasureEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectricMeasureEntityRepository extends CrudRepository<ElectricMeasureEntity, Long> {
    List<ElectricMeasureEntity> findAllByDateOfMeasuringAfter(LocalDateTime time);

    List<ElectricMeasureEntity> findAllByDateOfMeasuringBefore(LocalDateTime time);

    List<ElectricMeasureEntity> findAllByDateOfMeasuringBetween(LocalDateTime start, LocalDateTime end);

    List<ElectricMeasureEntity> findAll();

    ElectricMeasureEntity save(ElectricMeasureEntity entity);


    // List<ElectricMeasureEntity> saveAll(List<ElectricMeasureEntity> entities);


}
