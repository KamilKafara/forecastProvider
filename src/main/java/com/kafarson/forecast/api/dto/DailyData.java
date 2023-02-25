package com.kafarson.forecast.api.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class DailyData {
    private String[] time;
    private String[] sunrise;
    private String[] sunset;

    @JsonProperty("precipitation_sum")
    private Double[] precipitationSum;
}
