package com.smart.consumption;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDate;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@Value
@JsonDeserialize
@Builder
public class Consumptions {
    String group;
    Double value;
    LocalDate date;
}
