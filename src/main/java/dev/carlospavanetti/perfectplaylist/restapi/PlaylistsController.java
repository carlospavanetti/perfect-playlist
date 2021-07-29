package dev.carlospavanetti.perfectplaylist.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.carlospavanetti.perfectplaylist.models.Playlist;
import dev.carlospavanetti.perfectplaylist.services.PerfectPlaylistService;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/playlists")
public class PlaylistsController {
    @Autowired
    PerfectPlaylistService playlistService;

    @GetMapping("/city")
    public Flux<Playlist> playlistFromCity(@RequestParam("name") String name) {
        return playlistService.fromCityName(name);
    }

    @GetMapping("/coords")
    public Flux<Playlist> playlistFromCoords(@RequestParam("lat") double lat, @RequestParam("lon") double lon) {
        return playlistService.fromCoordinates(lat, lon);
    }
}
