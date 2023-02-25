package com.kafarson.forecast.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForecastDTO {
    private BigDecimal averagePrecipitation;
    private String sunrise;
    private String sunset;
    private String date;
    private double latitude;
    private double longitude;
}
