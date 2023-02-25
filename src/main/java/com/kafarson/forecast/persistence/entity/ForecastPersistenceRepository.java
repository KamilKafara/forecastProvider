package com.kafarson.forecast.persistence.entity;

import com.kafarson.forecast.persistence.ForecastEntity;

public interface ForecastPersistenceRepository {
    ForecastEntity save(ForecastEntity forecastEntity);
}
