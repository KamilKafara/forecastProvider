package com.kafarson.forecast.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HourlyData {
    private List<String> time;
    @JsonProperty("temperature_2m")
    private Double[] temperature;
    @JsonProperty("precipitation")
    private Double[] precipitation;
}
