package services;

import config.Initializations;
import enums.Constants;
import enums.TrafficEnums;
import models.BusLocationModel;
import models.BusTrafficModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HeatMapVisualizationService {
    private ArrayList<BusLocationModel> busLocationModels;
    private Map<String,String> routeMap = new HashMap<>();

    HeatMapVisualizationService() {
        try {
            busLocationModels = Initializations.initBusLocations();
        } catch (IOException e) {
            System.out.println("Initialization error");
            e.printStackTrace();
        }
    }

    public TrafficEnums getTrafficStatePer1Second(Double destination) throws Exception {
        if (destination > Constants.NORMAL_TRAFFIC_SPEED)
            return TrafficEnums.LOW_GREEN;
        if (destination < Constants.NORMAL_TRAFFIC_SPEED && destination > Constants.MEDIUM_TRAFFIC_SPEED)
            return TrafficEnums.MEDIUM_YELLOW;
        if (destination < Constants.MEDIUM_TRAFFIC_SPEED)
            return TrafficEnums.HIGH_RED;
        throw new Exception("Invalid Arguments");
    }

    public TrafficEnums getTrafficStatePer10Second(Double destination) throws Exception {
        return getTrafficStatePer1Second(destination / 10);
    }

    public TrafficEnums getTrafficStatePer100Second(Double destination) throws Exception {
        return getTrafficStatePer1Second(destination / 100);
    }

    public static void main(String[] args) {
        HeatMapVisualizationService heatMapVisualizationService = new HeatMapVisualizationService();
        BusTrafficModel trafficModel = new BusTrafficModel();
        try {
            trafficModel.setTrafficJamPer1Second(heatMapVisualizationService.getTrafficStatePer1Second(10.4).toString());
            trafficModel.setTrafficJamPer10Second(heatMapVisualizationService.getTrafficStatePer10Second(50.4).toString());
            trafficModel.setTrafficJamPer100Second(heatMapVisualizationService.getTrafficStatePer100Second(200.4).toString());

            //BusTrafficModel[trafficJamPer1Second=LOW_GREEN, trafficJamPer10Second=MEDIUM_YELLOW, trafficJamPer100Second=HIGH_RED]
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
