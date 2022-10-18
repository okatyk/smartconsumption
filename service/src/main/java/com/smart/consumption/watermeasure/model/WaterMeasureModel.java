package com.smart.consumption.watermeasure.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.smart.consumption.MeasureModel;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = WaterMeasureModel.WaterMeasureModelBuilder.class)
public class WaterMeasureModel implements MeasureModel {
    long id;
    String measuring;
    String unitOfMeasure;
    double measuredData;
    LocalDateTime dateOfMeasuring;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class WaterMeasureModelBuilder {
    }
}
