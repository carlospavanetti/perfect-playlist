package dev.carlospavanetti.perfectplaylist.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Playlist {
    private String name;
    private String externalUrl;
    private List<Track> tracks;
}
