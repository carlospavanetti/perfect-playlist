package dev.carlospavanetti.perfectplaylist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.carlospavanetti.perfectplaylist.models.Track;
import reactor.core.publisher.Flux;

@Service
public class PerfectPlaylistService {
    @Autowired
    private CurrentTemperatureService temperatureService;

    @Autowired
    private PlaylistStrategy playlistStrategy;

    @Autowired
    private PlaylistService playlistService;

    public Flux<Track> fromCityName(String name) {
        var temp = this.temperatureService.fromCityName(name);
        return this.playlistFromTemperature(temp);
    }

    public Flux<Track> fromCoordinates(double lat, double lon) {
        var temp = this.temperatureService.fromCoordinates(lat, lon);
        return this.playlistFromTemperature(temp);
    }

    private Flux<Track> playlistFromTemperature(double temp) {
        var type = this.playlistStrategy.perfectType(temp);
        return this.playlistService.ofType(type);
    }
}
