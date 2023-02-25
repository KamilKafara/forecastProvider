package com.kafarson.forecast.api.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HourlyData {
    private List<String> time;
    private List<Double> temperature2m;
    private List<Integer> relativeHumidity2m;
    private List<Double> windSpeed10m;
}
