package dev.carlospavanetti.perfectplaylist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfectPlaylistService {
    @Autowired
    private CurrentTemperatureService temperatureService;

    public String fromCityName(String name) {
        var temp = this.temperatureService.fromCityName(name);
        return this.playlistFromTemperature(temp);
    }

    public String fromCoordinates(double lat, double lon) {
        var temp = this.temperatureService.fromCoordinates(lat, lon);
        return this.playlistFromTemperature(temp);
    }

    private String playlistFromTemperature(double temp) {
        if (temp > 30)
            return "party";

        if (temp >= 15)
            return "pop music";

        if (temp >= 10)
            return "rock music";

        return "classical music";
    }
}
