package dev.carlospavanetti.perfectplaylist.services;

public interface CurrentTemperatureService {
    double fromCityName(String name);

    double fromCoordinates(double lat, double lon);
}
