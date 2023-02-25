package com.kafarson.forecast.persistence.entity;

import com.kafarson.forecast.persistence.ForecastEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForecastPersistence implements ForecastPersistenceRepository {

    private final ForecastRepository forecastRepository;

    @Autowired
    public ForecastPersistence(ForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
    }

    @Override
    public ForecastEntity save(ForecastEntity forecastEntity) {
        return forecastRepository.save(forecastEntity);
    }
}
