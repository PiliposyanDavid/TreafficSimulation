package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinates {
    @JsonProperty("latitude")
    private Double lat;

    @JsonProperty("longitude")
    private Double lon;


    public Coordinates() {
    }

    public Coordinates(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Coordinates.class.getSimpleName() + "[", "]")
                .add("lat=" + lat)
                .add("lon=" + lon)
                .toString();
    }
}
