package com.kafarson.forecast.api;

import com.kafarson.forecast.api.entity.ForecastData;
import org.springframework.stereotype.Service;

@Service
class ForecastApiImpl implements ForecastApi {
    @Override
    public ForecastData getForecast(double latitude, double longitude, int pastDays) {
        return null;
    }
}
