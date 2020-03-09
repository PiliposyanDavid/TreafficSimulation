package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusLocationModel {
    @JsonProperty("timestamp")
    private Long timestamp;

    @JsonProperty("lat")
    private Double lat;

    @JsonProperty("long")
    private Double lon;

    @JsonProperty("id")
    private String busNumber;

    public BusLocationModel() {
    }

    public BusLocationModel(Long timestamp, Double lat, Double lon, String busNumber) {
        this.timestamp = timestamp;
        this.lat = lat;
        this.lon = lon;
        this.busNumber = busNumber;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BusLocationModel.class.getSimpleName() + "[", "]")
                .add("timestamp=" + timestamp)
                .add("lat=" + lat)
                .add("lon=" + lon)
                .add("busNumber='" + busNumber + "'")
                .toString();
    }
}

