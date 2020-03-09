package models;

import java.util.StringJoiner;

public class BusTrafficModel {
    private String trafficJamPer1Second;
    private String trafficJamPer10Second;
    private String trafficJamPer100Second;

    public BusTrafficModel() {
    }


    public BusTrafficModel(String trafficJamPer1Second, String trafficJamPer10Second, String trafficJamPer100Second) {
        this.trafficJamPer1Second = trafficJamPer1Second;
        this.trafficJamPer10Second = trafficJamPer10Second;
        this.trafficJamPer100Second = trafficJamPer100Second;
    }

    public String getTrafficJamPer1Second() {
        return trafficJamPer1Second;
    }

    public void setTrafficJamPer1Second(String trafficJamPer1Second) {
        this.trafficJamPer1Second = trafficJamPer1Second;
    }

    public String getTrafficJamPer10Second() {
        return trafficJamPer10Second;
    }

    public void setTrafficJamPer10Second(String trafficJamPer10Second) {
        this.trafficJamPer10Second = trafficJamPer10Second;
    }

    public String getTrafficJamPer100Second() {
        return trafficJamPer100Second;
    }

    public void setTrafficJamPer100Second(String trafficJamPer100Second) {
        this.trafficJamPer100Second = trafficJamPer100Second;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BusTrafficModel.class.getSimpleName() + "[", "]")
                .add("trafficJamPer1Second=" + trafficJamPer1Second)
                .add("trafficJamPer10Second=" + trafficJamPer10Second)
                .add("trafficJamPer100Second=" + trafficJamPer100Second)
                .toString();
    }
}
