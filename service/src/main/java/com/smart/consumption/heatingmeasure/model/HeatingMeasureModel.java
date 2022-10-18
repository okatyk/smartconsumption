package com.smart.consumption.heatingmeasure.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.smart.consumption.MeasureModel;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = HeatingMeasureModel.HeatingMeasureModelBuilder.class)
public class HeatingMeasureModel implements MeasureModel {
    long id;
    String measuring;
    String unitOfMeasure;
    double measuredData;
    LocalDateTime dateOfMeasuring;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class HeatingMeasureModelBuilder {
    }
}
