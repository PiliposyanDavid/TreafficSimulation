package services;

import enums.TrafficEnums;

import java.util.ArrayList;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class LightsNearCoordinates {
    public static TrafficEnums getNearDegree(Double lat, Double lon, Map<String, String> route) {
        String coordinate = lat.toString() + "__" + lon.toString();
        SortedSet<String> keys = new TreeSet<>(route.keySet());

        ArrayList values = new ArrayList();

        Integer count = 0;
        Integer greenCount = 0;
        Integer yellowCount = 0;
        Integer redCount = 0;

        for (String key : keys) {
            if (!key.equals(coordinate)) {
                continue;
            }
            count++;
            if (count == 50) break;
            String value = route.get(key);

            if (value.equals("LOW_GREEN")) greenCount++;
            if (value.equals("MEDIUM_YELLOW")) yellowCount++;
            if (value.equals("HIGH_RED")) redCount++;

            values.add(value);
        }
        if (yellowCount > greenCount && yellowCount > redCount) return TrafficEnums.MEDIUM_YELLOW;
        if (greenCount > yellowCount && greenCount > redCount) return TrafficEnums.LOW_GREEN;
        if (redCount > greenCount && redCount > yellowCount) return TrafficEnums.HIGH_RED;
        return TrafficEnums.INVALID;
    }
}
