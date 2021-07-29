package dev.carlospavanetti.perfectplaylist.externalservices.spotify;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import dev.carlospavanetti.perfectplaylist.models.Playlist;
import dev.carlospavanetti.perfectplaylist.services.PlaylistService;
import reactor.core.publisher.Flux;

@Service
public class SpotifyPlaylistService implements PlaylistService {
    private SpotifyAuthorizationApi authzApi;
    private SpotifyPlaylistApi playlistApi;

    public SpotifyPlaylistService(@Value("${app.spotify.authorization-api.url}") String authzUrl,
            @Value("${app.spotify.authorization-api.client-id}") String clientId,
            @Value("${app.spotify.authorization-api.client-secret}") String clientSecret,
            @Value("${app.spotify.playlist-api.url}") String playlistUrl) {
        this.authzApi = SpotifyAuthorizationApi.connection(authzUrl, this.authzToken(clientId, clientSecret));
        var authz = this.authzApi.authorize();
        this.playlistApi = SpotifyPlaylistApi.connection(playlistUrl, authz.tokenType + " " + authz.accessToken);
    }

    @Override
    public Flux<Playlist> ofType(String type) {
        var res = playlistApi.playlistFromType(type);

        return Flux.fromIterable(res.getItems()).map(PlaylistResponse.Item::asPlaylist);
    }

    private String authzToken(String clientId, String clientSecret) {
        var raw = clientId + ":" + clientSecret;
        var encoded = Base64.getEncoder().encodeToString(raw.getBytes());
        return "Basic " + encoded;
    }
}
