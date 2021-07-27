package dev.carlospavanetti.perfectplaylist.externalservices.openweathermap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import dev.carlospavanetti.perfectplaylist.services.CurrentTemperatureService;

@Service
public class OpenWeatherMapService implements CurrentTemperatureService {
    private OpenWeatherMapApi apiConnection;

    public OpenWeatherMapService( //
            @Value("${app.openweathermap.url}") String url, //
            @Value("${app.openweathermap.appid}") String appid) {
        this.apiConnection = OpenWeatherMapApi.connection(url, appid);
    }

    @Override
    public double fromCityName(String name) {
        var response = this.apiConnection.fromCityName(name);
        return response.getTemperature();
    }

    @Override
    public double fromCoordinates(double lat, double lon) {
        var response = this.apiConnection.fromCoordinates(lat, lon);
        return response.getTemperature();
    }
}
