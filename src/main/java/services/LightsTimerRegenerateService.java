package services;

import config.TrafficLights;
import enums.TrafficEnums;
import models.Coordinates;

import static services.HeatMapVisualizationService.getTrafficRouteMap;
import static services.LightsNearCoordinates.getNearDegree;

public class LightsTimerRegenerateService {
    private Coordinates coordinates = new Coordinates(40.273419, 44.620235);
    private TrafficLights trafficLight = new TrafficLights(coordinates, "abovyan_2_teryan_1", "tested", 20, 30);

    private TrafficLights regenerateLightTime() {
        TrafficEnums value = getNearDegree(coordinates.getLat(), coordinates.getLon(), getTrafficRouteMap());

        if (value.equals(TrafficEnums.INVALID)) {
            System.out.println("Invalid date, error");
            return trafficLight;
        }

        if (value.equals(TrafficEnums.HIGH_RED)) {
            System.out.println("Degree is red, change red light to 30 and green to 10");
            trafficLight.setGreenMaxTime(10);
            trafficLight.setRedMaxTime(30);
            return trafficLight;
        }

        if (value.equals(TrafficEnums.MEDIUM_YELLOW)) {
            System.out.println("Degree is yellow, change red light to 25 and green to 20");
            trafficLight.setGreenMaxTime(20);
            trafficLight.setRedMaxTime(25);
            return trafficLight;
        }

        if (value.equals(TrafficEnums.LOW_GREEN)) {
            System.out.println("Degree is green, change red light to 15 and green to 40");
            trafficLight.setGreenMaxTime(40);
            trafficLight.setRedMaxTime(15);
            return trafficLight;
        }
        return trafficLight;
    }
}
