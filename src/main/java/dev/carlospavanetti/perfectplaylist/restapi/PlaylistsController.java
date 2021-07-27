package dev.carlospavanetti.perfectplaylist.restapi;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.carlospavanetti.perfectplaylist.services.CurrentTemperatureService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/playlists")
public class PlaylistsController {
    @Autowired
    CurrentTemperatureService weatherService;

    @GetMapping("/city")
    public Mono<Map<String, Double>> playlistFromCity(@RequestParam("name") String name) {
        double temp = weatherService.fromCityName(name);
        return Mono.just(Map.of("temperature", temp));
    }

    @GetMapping("/coords")
    public Mono<Map<String, Double>> playlistFromCoords(@RequestParam("lat") double lat,
            @RequestParam("lon") double lon) {
        double temp = weatherService.fromCoordinates(lat, lon);
        return Mono.just(Map.of("temperature", temp));
    }
}
