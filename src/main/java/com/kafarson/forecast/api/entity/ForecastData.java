package com.kafarson.forecast.api.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ForecastData {
    private double latitude;
    private double longitude;
    private double generationTimeMs;
    private int utcOffsetSeconds;
    private String timezone;
    private String timezoneAbbreviation;
    private double elevation;
    private HourlyUnit hourlyUnits;
    private HourlyData hourly;
}
