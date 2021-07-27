package dev.carlospavanetti.perfectplaylist.externalservices.openweathermap;

import feign.Feign;
import feign.Param;
import feign.RequestLine;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

public interface OpenWeatherMapApi {
    @RequestLine("GET /weather?q={cityName}")
    CurrentWeatherResponse fromCityName(@Param("cityName") String name);

    @RequestLine("GET /weather?lat={lat}&lon={lon}")
    CurrentWeatherResponse fromCoordinates(@Param("lat") double lat, @Param("lon") double lon);

    static OpenWeatherMapApi connection(String url, String appid) {
        return Feign.builder() //
                .encoder(new GsonEncoder()) //
                .decoder(new GsonDecoder()) //
                .requestInterceptor(template -> {
                    template.query("appid", appid);
                    template.query("units", "metric");
                }).target(OpenWeatherMapApi.class, url);
    }
}
