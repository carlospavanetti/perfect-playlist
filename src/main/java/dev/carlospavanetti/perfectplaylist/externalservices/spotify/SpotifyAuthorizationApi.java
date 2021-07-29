package dev.carlospavanetti.perfectplaylist.externalservices.spotify;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import feign.Body;
import feign.Feign;
import feign.Headers;
import feign.RequestLine;
import feign.form.FormEncoder;
import feign.gson.GsonDecoder;

public interface SpotifyAuthorizationApi {
    @RequestLine("POST /token")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @Body("grant_type=client_credentials")
    AuthorizationResponse authorize();

    static SpotifyAuthorizationApi connection(String url, String token) {
        var gson = new GsonBuilder() //
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES) //
                .create();
        return Feign.builder() //
                .encoder(new FormEncoder()) //
                .decoder(new GsonDecoder(gson)) //
                .requestInterceptor(template -> {
                    template.header("authorization", token);
                }).target(SpotifyAuthorizationApi.class, url);
    }

    static class AuthorizationResponse {
        String accessToken;
        String tokenType;
        int expiresIn;
    }
}
