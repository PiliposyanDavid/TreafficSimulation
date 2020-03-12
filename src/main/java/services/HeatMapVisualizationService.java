package services;

import config.Initializations;
import enums.Constants;
import enums.TrafficEnums;
import models.BusLocationModel;
import models.BusTrafficModel;

import java.io.IOException;
import java.util.*;

public class HeatMapVisualizationService {
    private ArrayList<BusLocationModel> busLocationModels;
    private Map<String, String> routeMap = new TreeMap<String, String>();
    BusTrafficModel busTrafficModel = new BusTrafficModel();

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

    private void getTrafficFromTestResource() {
        Random random = new Random();

        busLocationModels.forEach(busLocationModel -> {
            double randomValue = 0.00001 + (0.0001 - 0.00001) * random.nextDouble();

            Double lat1 = busLocationModel.getLat() + randomValue;
            Double lon1 = busLocationModel.getLon() + randomValue;


            Double lat2 = busLocationModels.get(0).getLat();
            Double lon2 = busLocationModels.get(0).getLon();

            Double distance = Destination.distance(lat1, lat2, lon1, lon2);

            try {
                routeMap.put(lat1.toString() + "__" + lon1.toString(), getTrafficStatePer1Second(distance).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        SortedSet<String> keys = new TreeSet<>(routeMap.keySet());
        System.out.println(keys);
        for (String key : keys) {
            String value = routeMap.get(key);
            System.out.println("{" + key + " : " + value + "}");
        }
    }

    private void getTrafficFrom() throws Exception {

        while (true) {
            busTrafficModel.setTrafficJamPer1Second(
                    getTrafficStatePer1Second(Destination.distance(busLocationModels.get(0).getLat(),
                            busLocationModels.get(1).getLat(),
                            busLocationModels.get(0).getLon(),
                            busLocationModels.get(1).getLon())
                    ).toString());

            busTrafficModel.setTrafficJamPer10Second(
                    getTrafficStatePer10Second(Destination.distance(busLocationModels.get(0).getLat(),
                            busLocationModels.get(10).getLat(),
                            busLocationModels.get(0).getLon(),
                            busLocationModels.get(10).getLon())
                    ).toString());

            busTrafficModel.setTrafficJamPer100Second(
                    getTrafficStatePer100Second(Destination.distance(busLocationModels.get(0).getLat(),
                            busLocationModels.get(100).getLat(),
                            busLocationModels.get(0).getLon(),
                            busLocationModels.get(100).getLon())
                    ).toString());


            for (int ix = 1; ix < 100; ++ix) {
                routeMap.put(
                        busLocationModels.get(ix - 1).getLat().toString() + ":" + busLocationModels.get(ix - 1).getLon(),
                        getTrafficStatePer1Second(Destination.distance(
                                busLocationModels.get(ix - 1).getLat(), busLocationModels.get(ix).getLat(),
                                busLocationModels.get(ix - 1).getLon(), busLocationModels.get(ix).getLon())
                        ).toString());
            }

            busLocationModels.clear();
            updateBusLocations();
        }
    }

    public static void main(String[] args) {
        HeatMapVisualizationService heatMapVisualizationService = new HeatMapVisualizationService();
        heatMapVisualizationService.getTrafficFromTestResource();
    }
}

