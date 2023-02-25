package com.kafarson.forecast.persistence.entity;

import com.kafarson.forecast.persistence.ForecastEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForecastRepository extends JpaRepository<ForecastEntity, Long> {
}
