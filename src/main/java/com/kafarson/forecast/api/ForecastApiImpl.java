package com.kafarson.forecast.api;

import com.kafarson.forecast.api.dto.DailyData;
import com.kafarson.forecast.api.dto.ForecastDTO;
import com.kafarson.forecast.api.dto.ForecastData;
import com.kafarson.forecast.persistence.ForecastEntity;
import com.kafarson.forecast.persistence.entity.ForecastPersistenceRepository;
import com.kafarson.forecast.utils.DateUtil;
import com.kafarson.forecast.utils.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class ForecastApiImpl implements ForecastApi {
    private static final String FORECAST_API_URL = "https://api.open-meteo.com/v1/forecast";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String HOURLY = "hourly";
    private static final String DAILY = "daily";
    private static final String TIMEZONE = "timezone";
    private static final String PAST_DAYS = "past_days";

    private final FetchApi fetchApi;
    private final ForecastPersistenceRepository forecastPersistence;

    @Autowired
    public ForecastApiImpl(FetchApi fetchApi, ForecastPersistenceRepository forecastPersistence) {
        this.fetchApi = fetchApi;
        this.forecastPersistence = forecastPersistence;
    }

    @Override
    public List<ForecastDTO> getForecast(double latitude, double longitude, int pastDays) throws IOException {
        Map<String, String> params = setupAttributes(latitude, longitude, pastDays);
        String queryParam = UrlUtil.createQueryParam(params);
        ForecastData forecastDataResult = fetchApi.getDataByType(FORECAST_API_URL + queryParam, ForecastData.class);
        forecastPersistence.save(setupForecastEntity(params, queryParam));
        return getSpecificForecast(forecastDataResult);
    }

    private static Map<String, String> setupAttributes(double latitude, double longitude, int pastDays) {
        Map<String, String> params = new HashMap<>();
        params.put(LATITUDE, String.valueOf(latitude));
        params.put(LONGITUDE, String.valueOf(longitude));
        params.put(HOURLY, "temperature_2m,precipitation");
        params.put(DAILY, "sunrise,sunset,precipitation_sum");
        params.put(TIMEZONE, "Europe%2FBerlin");
        params.put(PAST_DAYS, String.valueOf(pastDays));
        return params;
    }

    private ForecastEntity setupForecastEntity(Map<String, String> params, String queryParam) {
        ForecastEntity entity = new ForecastEntity();
        entity.setQueryParam(queryParam);
        entity.setLatitude(Double.parseDouble(params.get(LATITUDE)));
        entity.setLongitude(Double.parseDouble(params.get(LONGITUDE)));
        entity.setPastDays(Integer.parseInt(params.get(PAST_DAYS)));
        return entity;
    }

    public List<ForecastDTO> getSpecificForecast(ForecastData forecastEntity) {
        List<ForecastDTO> forecastDTOS = new ArrayList<>();
        DailyData dailyForecast = forecastEntity.getDaily();
        List<String> dailyTimeList = Arrays.stream(dailyForecast.getTime()).toList();
        List<String> dailySunrise = Arrays.stream(dailyForecast.getSunrise()).toList();
        List<String> dailySunset = Arrays.stream(dailyForecast.getSunset()).toList();
        List<Double> dailyPrecipitationSum = Arrays.stream(forecastEntity.getDaily().getPrecipitationSum()).toList();

        if (!listsSizeAreEqual(dailyTimeList, dailySunrise, dailySunset, dailyPrecipitationSum)) {
            return new ArrayList<>();
        }

        List<String> hourlyTime = forecastEntity.getHourly().getTime();
        List<Double> hourlyPrecipitation = Arrays.stream(forecastEntity.getHourly().getPrecipitation()).toList();
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        for (int index = 0; index < dailyTimeList.size(); index++) {
            if (DateUtil.convertDate(dailyTimeList.get(index)).isBefore(today)) {
                ForecastDTO dailyDTO = new ForecastDTO();
                dailyDTO.setDate(dailyTimeList.get(index));
                dailyDTO.setLatitude(forecastEntity.getLatitude());
                dailyDTO.setLongitude(forecastEntity.getLongitude());
                dailyDTO.setSunset(dailySunset.get(index));
                dailyDTO.setSunrise(dailySunrise.get(index));
                prepareHourlyAverage(hourlyTime, hourlyPrecipitation, dailyDTO);
                forecastDTOS.add(dailyDTO);
            }
        }
        return forecastDTOS;
    }

    private void prepareHourlyAverage(List<String> hourlyTime, List<Double> hourlyPrecipitation, ForecastDTO dailyDTO) {
        int hourCounter = 0;
        double currentPrecipitationSum = 0.0;
        for (int hourlyIndex = 0; hourlyIndex < hourlyPrecipitation.size(); hourlyIndex++) {
            LocalDateTime currentDateTime = DateUtil.convertDateTime(hourlyTime.get(hourlyIndex));
            if (isDay(dailyDTO, currentDateTime)) {
                currentPrecipitationSum += hourlyPrecipitation.get(hourlyIndex);
                hourCounter++;
            }
        }
        if (hourCounter != 0) {
            BigDecimal average = BigDecimal.valueOf(currentPrecipitationSum / hourCounter).setScale(2, RoundingMode.UP);
            dailyDTO.setAveragePrecipitation(average);
        }
    }

    private boolean isDay(ForecastDTO dailyDTO, LocalDateTime currentDateTime) {
        return currentDateTime.isAfter(DateUtil.convertDateTime(dailyDTO.getSunrise())) &&
                currentDateTime.isBefore(DateUtil.convertDateTime(dailyDTO.getSunset()));
    }

    private boolean listsSizeAreEqual(List<String> dailyTimeList,
                                      List<String> dailySunrise,
                                      List<String> dailySunset,
                                      List<Double> dailyPrecipitation) {
        return dailyTimeList.size() == dailySunrise.size() &&
                dailySunset.size() == dailyPrecipitation.size();
    }
}
