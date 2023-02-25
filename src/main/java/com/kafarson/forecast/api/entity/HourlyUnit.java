package com.kafarson.forecast.api.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HourlyUnit {
    private String time;
    private String temperature2m;
    private String relativeHumidity2m;
    private String windSpeed10m;
}
