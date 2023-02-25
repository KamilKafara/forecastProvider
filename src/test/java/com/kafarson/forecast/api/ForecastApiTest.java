package com.kafarson.forecast.api;

import com.kafarson.forecast.api.dto.ForecastData;
import com.kafarson.forecast.api.dto.HourlyData;
import com.kafarson.forecast.api.dto.HourlyUnit;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Collections;


@RunWith(MockitoJUnitRunner.class)
public class ForecastApiTest {

    @InjectMocks
    private ForecastApiImpl forecastApi;

    @Test
    @Ignore
    public void getForecastShouldPass() throws IOException {
        //given
        double latitude = 3.1f;
        double longitude = -4.6f;
        int pastDays = 5;
        //when
//        List<ForecastDTO> forecastResult = forecastApi.getForecast(latitude, longitude, pastDays);
//        List<ForecastDTO> expectedResult = Collections.singletonList(setupForecastData(latitude, longitude));
//        then
//        assertEquals(expectedResult.get(0).getLongitude(), forecastResult.get(0).getLongitude(), 0);
//        assertEquals(expectedResult.get(0).getLatitude(), forecastResult.get(0).getLatitude(), 0);
//        assertEquals(expectedResult.get(0).getHourly().getTemperature()[0], forecastResult.get(0).getHourly().getTemperature()[0], 0);
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
//                .temperature2m(new HourlyData[]{3.1})
//                .relativeHumidity2m(Collections.singletonList(88))
//                .windSpeed10m(Collections.singletonList(2.9))
                .build();
    }
}
