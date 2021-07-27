package dev.carlospavanetti.perfectplaylist.restapi;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.carlospavanetti.perfectplaylist.services.PerfectPlaylistService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/playlists")
public class PlaylistsController {
    @Autowired
    PerfectPlaylistService playlistService;

    @GetMapping("/city")
    public Mono<Map<String, String>> playlistFromCity(@RequestParam("name") String name) {
        String playlist = playlistService.fromCityName(name);
        return Mono.just(Map.of("playlist", playlist));
    }

    @GetMapping("/coords")
    public Mono<Map<String, String>> playlistFromCoords(@RequestParam("lat") double lat,
            @RequestParam("lon") double lon) {
        String playlist = playlistService.fromCoordinates(lat, lon);
        return Mono.just(Map.of("playlist", playlist));
    }
}
