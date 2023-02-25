package com.kafarson.forecast.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HourlyUnit {
    private String time;
    private String temperature2m;
    private String relativeHumidity2m;
    private String windSpeed10m;
}
