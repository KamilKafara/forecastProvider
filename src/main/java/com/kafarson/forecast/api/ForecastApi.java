package com.kafarson.forecast.api;

import com.kafarson.forecast.api.entity.ForecastData;

public interface ForecastApi {

    ForecastData getForecast(double latitude, double longitude, int pastDays);
}
