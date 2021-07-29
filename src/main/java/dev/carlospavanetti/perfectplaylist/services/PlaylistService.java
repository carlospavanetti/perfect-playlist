package dev.carlospavanetti.perfectplaylist.services;

import dev.carlospavanetti.perfectplaylist.models.Playlist;
import reactor.core.publisher.Flux;

public interface PlaylistService {
    Flux<Playlist> ofType(String type);
}
