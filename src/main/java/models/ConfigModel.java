package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.StringJoiner;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigModel {
    @JsonProperty("bus_id")
    private Long busId;

    @JsonProperty("bus_data")
    private ArrayList<BusLocationModel> busLocationModels;

    public ConfigModel(Long busId, ArrayList<BusLocationModel> busLocationModels) {
        this.busId = busId;
        this.busLocationModels = busLocationModels;
    }

    public ConfigModel() {
    }

    public Long getBusId() {
        return busId;
    }

    public void setBusId(Long busId) {
        this.busId = busId;
    }

    public ArrayList<BusLocationModel> getBusLocationModels() {
        return busLocationModels;
    }

    public void setBusLocationModels(ArrayList<BusLocationModel> busLocationModels) {
        this.busLocationModels = busLocationModels;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ConfigModel.class.getSimpleName() + "[", "]")
                .add("busId=" + busId)
                .add("busLocationModels=" + busLocationModels)
                .toString();
    }
}
