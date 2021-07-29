package dev.carlospavanetti.perfectplaylist.externalservices.spotify;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import feign.Feign;
import feign.Param;
import feign.RequestLine;
import feign.gson.GsonDecoder;

public interface SpotifyPlaylistApi {
    @RequestLine("GET /search?q={type}&type=playlist")
    PlaylistResponse playlistFromType(@Param("type") String type);

    @RequestLine("GET /playlists/{id}")
    String playlistFromId(@Param("id") String id);

    static SpotifyPlaylistApi connection(String url, String token) {
        var gson = new GsonBuilder() //
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES) //
                .registerTypeAdapterFactory( //
                        new PlaylistResponse.PlaylistTypeAdapterFactory())
                .create();
        return Feign.builder() //
                .decoder(new GsonDecoder(gson)) //
                .requestInterceptor(template -> {
                    template.header("Authorization", token);
                }).target(SpotifyPlaylistApi.class, url);
    }
}
