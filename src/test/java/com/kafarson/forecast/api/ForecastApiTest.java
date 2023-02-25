package com.kafarson.forecast.api;

import com.kafarson.forecast.api.entity.ForecastData;
import com.kafarson.forecast.api.entity.HourlyData;
import com.kafarson.forecast.api.entity.HourlyUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class ForecastApiTest {

    @InjectMocks
    private ForecastApiImpl forecastApi;

    @Test
    public void getForecastShouldPass() {
        //given
        double latitude = 3.1f;
        double longitude = -4.6f;
        int pastDays = 5;
        //when
        ForecastData forecastResult = forecastApi.getForecast(latitude, longitude, pastDays);
        ForecastData expectedResult = setupForecastData(latitude, longitude);
        //then
        assertEquals(expectedResult.getLongitude(), forecastResult.getLongitude(), 0);
        assertEquals(expectedResult.getLatitude(), forecastResult.getLatitude(), 0);
        assertEquals(expectedResult.getHourly().getTemperature2m().get(0), forecastResult.getHourly().getTemperature2m().get(0), 0);
    }

    private ForecastData setupForecastData(double latitude, double longitude) {
        return ForecastData.builder()
                .latitude(latitude)
                .longitude(longitude)
                .hourlyUnits(setupHourlyUnit())
                .hourly(setupHourlyData())
                .build();
    }

    private HourlyUnit setupHourlyUnit() {
        return HourlyUnit.builder()
                .time("iso8601")
                .temperature2m("°C")
                .relativeHumidity2m("%")
                .windSpeed10m("km/h")
                .build();
    }

    private HourlyData setupHourlyData() {
        return HourlyData.builder()
                .time(Collections.singletonList("2023-02-15T00:00"))
                .temperature2m(Collections.singletonList(3.1))
                .relativeHumidity2m(Collections.singletonList(88))
                .windSpeed10m(Collections.singletonList(2.9))
                .build();
    }
}
