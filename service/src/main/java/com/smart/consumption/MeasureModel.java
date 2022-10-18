package com.smart.consumption;

import java.time.LocalDateTime;

public interface MeasureModel {

    String getMeasuring();

    String getUnitOfMeasure();

    double getMeasuredData();

    LocalDateTime getDateOfMeasuring();
}
