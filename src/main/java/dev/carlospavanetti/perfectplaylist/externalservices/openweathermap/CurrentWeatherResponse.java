package dev.carlospavanetti.perfectplaylist.externalservices.openweathermap;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;

public class CurrentWeatherResponse {
    @SerializedName("main")
    @JsonAdapter(MainDeserializer.class)
    private double temperature;

    public double getTemperature() {
        return this.temperature;
    }

    public static class MainDeserializer implements JsonDeserializer<Double> {
        @Override
        public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            return json.getAsJsonObject().get("temp").getAsDouble();
        }
    }
}
