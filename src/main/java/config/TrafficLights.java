package config;

import models.Coordinates;

import java.util.StringJoiner;

public class TrafficLights {
    private Coordinates coordinates;
    private String prefixName;
    private String other;
    private String redMaxTime;

    private String greenMaxTime;

    public TrafficLights(Coordinates coordinates, String prefixName, String other, String redMaxTime, String greenMaxTime) {
        this.coordinates = coordinates;
        this.prefixName = prefixName;
        this.other = other;
        this.redMaxTime = redMaxTime;
        this.greenMaxTime = greenMaxTime;
    }

    public TrafficLights() {
    }

    public String getGreenMaxTime() {
        return greenMaxTime;
    }

    public void setGreenMaxTime(String greenMaxTime) {
        this.greenMaxTime = greenMaxTime;
    }

    public String getRedMaxTime() {
        return redMaxTime;
    }

    public void setRedMaxTime(String redMaxTime) {
        this.redMaxTime = redMaxTime;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Double lat, Double lon) {
        this.coordinates.setLat(lat);
        this.coordinates.setLon(lon);
    }

    public String getPrefixName() {
        return prefixName;
    }

    public void setPrefixName(String prefixName) {
        this.prefixName = prefixName;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TrafficLights.class.getSimpleName() + "[", "]")
                .add("coordinates=" + coordinates)
                .add("prefixName='" + prefixName + "'")
                .add("other='" + other + "'")
                .add("redMaxTime='" + redMaxTime + "'")
                .add("greenMaxTime='" + greenMaxTime + "'")
                .toString();
    }
}
