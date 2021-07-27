package dev.carlospavanetti.perfectplaylist.services;

import dev.carlospavanetti.perfectplaylist.models.Track;
import reactor.core.publisher.Flux;

public interface PlaylistService {
    Flux<Track> ofType(String type);
}
