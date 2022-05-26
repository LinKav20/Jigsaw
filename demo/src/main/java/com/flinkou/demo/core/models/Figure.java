package com.flinkou.demo.core.models;

import java.util.ArrayList;

/**
 * Entity of figure.
 */
public class Figure {
    // List of figure coordinates.
    ArrayList<Coordinate> coords;

    /**
     * Constructor with coordinates.
     *
     * @param coords Coordinate od figure.
     */
    public Figure(ArrayList<Coordinate> coords) {
        this.coords = coords;
    }

    /**
     * Get figure coordinates.
     *
     * @return List fo coordinates.
     */
    public ArrayList<Coordinate> getCoords() {
        return coords;
    }

    /**
     * Get coordinate by index.
     *
     * @param index Index of coordinate.
     * @return Coordinate by index.
     */
    public Coordinate get(int index) {
        if (index >= 0 && index < coords.size()) {
            return coords.get(index);
        }

        throw new RuntimeException("Index out of range.");
    }

    /**
     * Check if figure contains coordinate.
     *
     * @param coordinate Coordinate to check.
     * @return True if figure contains coordinate, else false.
     */
    public boolean contain(Coordinate coordinate) {
        return coords.contains(coordinate);
    }
}
