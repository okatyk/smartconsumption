package com.smart.consumption.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "HEATING_MEASURES")
@Data
public class HeatingMeasureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "MEASURING")
    String measuring;
    @Column(name = "UNIT_OF_MEASURE")
    String unitOfMeasure;
    @Column(name = "MEASURED_DATA")
    double measuredData;
    @Column(name = "DATE_OF_MEASURING")
    LocalDateTime dateOfMeasuring;
}
