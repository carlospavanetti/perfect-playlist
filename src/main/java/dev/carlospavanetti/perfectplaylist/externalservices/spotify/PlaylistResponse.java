package dev.carlospavanetti.perfectplaylist.externalservices.spotify;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import dev.carlospavanetti.perfectplaylist.models.Playlist;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PlaylistResponse {
    private List<Item> items;

    private String href;
    private int limit;
    private int offset;
    private String next;
    private String previous;
    private int total;

    @Data
    @ToString
    static class Item {
        private String id;
        private String name;

        public Playlist asPlaylist() {
            var basePlayListUrl = "https://open.spotify.com/playlist/";
            return new Playlist(this.getName(), basePlayListUrl + this.getId(), null);
        }
    }

    static class PlaylistTypeAdapterFactory implements TypeAdapterFactory {

        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
            return new TypeAdapter<T>() {
                public void write(JsonWriter out, T value) throws IOException {
                    delegate.write(out, value);
                }

                public T read(JsonReader in) throws IOException {
                    if (type.getType() != PlaylistResponse.class)
                        return delegate.read(in);
                    in.beginObject();
                    in.nextName();
                    final T object = delegate.read(in);
                    in.endObject();
                    return object;
                }
            };
        }
    }
}
