package com.smart.consumption;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;
import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@JsonDeserialize
public class ConsumptionResponse<TYPE> {
    @JsonProperty("consumptions")
    List<TYPE> consumptionList;
    @JsonProperty("sumlist")
    List<Consumptions> consumptionsList;
    @JsonProperty("daily")
    List<Consumptions> daily;
    @NonFinal
    double min;
    @NonFinal
    double max;
}
