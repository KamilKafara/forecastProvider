package com.kafarson.forecast.api;

import com.kafarson.forecast.api.dto.ForecastDTO;

import java.io.IOException;
import java.util.List;

public interface ForecastApi {

    List<ForecastDTO> getForecast(double latitude, double longitude, int pastDays) throws IOException;
}
