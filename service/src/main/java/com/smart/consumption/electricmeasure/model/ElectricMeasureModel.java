package com.smart.consumption.electricmeasure.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.smart.consumption.MeasureModel;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = ElectricMeasureModel.ElectricMeasureModelBuilder.class)
public class ElectricMeasureModel implements MeasureModel {

    String measuring;
    String unitOfMeasure;
    double measuredData;
    LocalDateTime dateOfMeasuring;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class ElectricMeasureModelBuilder {
    }
}
