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
import java.util.Random;

public class HeatMapVisualizationService {
    private ArrayList<BusLocationModel> busLocationModels;
    private Map<String, String> routeMap = new HashMap<>();

    HeatMapVisualizationService() {
        try {
            busLocationModels = Initializations.initBusLocations();
        } catch (IOException e) {
            System.out.println("Initialization error");
            e.printStackTrace();
        }
    }

    void updateBusLocations() {
        try {
            busLocationModels = Initializations.initBusLocations();
        } catch (IOException e) {
            System.out.println("Initialization error");
            e.printStackTrace();
        }
    }

    private TrafficEnums getTrafficStatePer1Second(Double destination) throws Exception {
        if (destination > Constants.NORMAL_TRAFFIC_SPEED)
            return TrafficEnums.LOW_GREEN;
        if (destination < Constants.NORMAL_TRAFFIC_SPEED && destination > Constants.MEDIUM_TRAFFIC_SPEED)
            return TrafficEnums.MEDIUM_YELLOW;
        if (destination < Constants.MEDIUM_TRAFFIC_SPEED)
            return TrafficEnums.HIGH_RED;
        throw new Exception("Invalid Arguments");
    }

    private TrafficEnums getTrafficStatePer10Second(Double destination) throws Exception {
        return getTrafficStatePer1Second(destination / 10);
    }

    private TrafficEnums getTrafficStatePer100Second(Double destination) throws Exception {
        return getTrafficStatePer1Second(destination / 100);
    }

    public static void main(String[] args) {
        HeatMapVisualizationService heatMapVisualizationService = new HeatMapVisualizationService();
        BusTrafficModel trafficModel = new BusTrafficModel();
        Random rand = new Random();

        Double randomDataPer1Second;
        Double randomDataPer10Second;
        Double randomDataPer100Second;

        for (int i = 0; i < 10; i++) {

            randomDataPer1Second = 1 + (18 - 1) * rand.nextDouble();
            randomDataPer10Second = 1 + (180 - 1) * rand.nextDouble();
            randomDataPer100Second = 1 + (1800 - 1) * rand.nextDouble();

            try {
                trafficModel.setTrafficJamPer1Second(heatMapVisualizationService.getTrafficStatePer1Second(randomDataPer1Second).toString());
                trafficModel.setTrafficJamPer10Second(heatMapVisualizationService.getTrafficStatePer10Second(randomDataPer10Second).toString());
                trafficModel.setTrafficJamPer100Second(heatMapVisualizationService.getTrafficStatePer100Second(randomDataPer100Second).toString());

                System.out.println("Iteration " + i + ", bus traffic \n" + trafficModel);
                System.out.println("---------------------------------------");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}

