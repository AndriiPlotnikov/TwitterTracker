package dev.aplotnikov.configuration;

import lombok.Getter;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Twitter Location information
 */
@Getter
public enum TwitterLocation {

    NEW_YORK_LOCATION_COORDINATES(new double[][]{{-74.93, 40.23}, {-73.43, 41.23}}, "New York"), //40.730610, -73.935242.
    CHICAGO_LOCATION_COORDINATES(new double[][]{{-88.22, 41.38}, {-87.22, 42.38}}, "Chicago"), //41.881832, -87.623177
    SAN_FRANCISCO_LOCATION_COORDINATES(new double[][]{{-122.75, 36.8}, {-121.75, 37.8}}, "San Francisco"); //37.773972, -122.431297.

    private final double[][] coordinates;
    private final String name;

    /**
     * default all-args constructor
     *
     * @param coordinates coordinates area two arrays, first SW coordinates, followed by NE coordinates. Forms area
     * @param name name of location
     */
    private TwitterLocation(double[][] coordinates, String name) {
        this.coordinates = coordinates;
        this.name = name;
    }

    /**
     * get coordinates of all locations
     *
     * @return coordinates of all locations
     */
    public static double[][] getAllLocations() {
        double[][] array = new double[][]{};
        for (TwitterLocation location : TwitterLocation.values()) {
            ArrayUtils.addAll(array, location.coordinates);
        }

        return array;
    }

    /**
     * get coordinates of filtered locations
     *
     * @param names filter for locations
     * @return coordinates of filtered locations
     */
    public static double[][] getAllLocationsByName(List<String> names) {
        List<TwitterLocation> filteredLocations = findAllByName(names);

        double[][] array = new double[][]{};
        for (TwitterLocation location : filteredLocations) {
            array = ArrayUtils.addAll(array, location.coordinates);
        }

        return array;
    }

    private static List<TwitterLocation> findAllByName(List<String> names) {
        return Arrays.stream(TwitterLocation.values()).filter(location -> names.contains(location.getName())).collect(Collectors.toList());
    }

}
