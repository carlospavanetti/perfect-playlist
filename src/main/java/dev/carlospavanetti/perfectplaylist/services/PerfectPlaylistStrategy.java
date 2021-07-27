package dev.carlospavanetti.perfectplaylist.services;

import org.springframework.stereotype.Service;

@Service
public class PerfectPlaylistStrategy implements PlaylistStrategy {
    @Override
    public String perfectType(double temperature) {
        if (temperature > 30)
            return "party";

        if (temperature >= 15)
            return "pop music";

        if (temperature >= 10)
            return "rock music";

        return "classical music";
    }
}
