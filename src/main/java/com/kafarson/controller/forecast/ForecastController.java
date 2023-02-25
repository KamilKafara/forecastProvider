package com.kafarson.controller.forecast;

import com.kafarson.forecast.api.ForecastApi;
import com.kafarson.forecast.api.dto.ForecastDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/forecast")
public class ForecastController {
    private final ForecastApi forecastApi;

    public ForecastController(ForecastApi forecastApi) {
        this.forecastApi = forecastApi;
    }

    @GetMapping
    public List<ForecastDTO> getData(@RequestParam("latitude") double latitude,
                                     @RequestParam("longitude") double longitude,
                                     @RequestParam("pastDays") int pastDays) throws IOException {
        return forecastApi.getForecast(latitude, longitude, pastDays);
    }

}
