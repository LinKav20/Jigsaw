package com.flinkou.demo.models;

import com.flinkou.demo.core.models.Coordinate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CoordinateTests {

    @Test
    public void GetX_Coordinate_Equals() {
        Coordinate coordinate = new Coordinate(3, 4);

        int actualX = coordinate.X();
        int expectedX = 3;

        Assertions.assertEquals(expectedX, actualX);
    }

    @Test
    public void GetY_Coordinate_Equals() {
        Coordinate coordinate = new Coordinate(3, 4);

        int actualY = coordinate.Y();
        int expectedY = 4;

        Assertions.assertEquals(expectedY, actualY);
    }

    @Test
    public void EqualCoords_Coordinates_Equals() {
        Coordinate coordinateFirst = new Coordinate(3, 4);
        Coordinate coordinateSecond = new Coordinate(3, 4);

        boolean actualResult = coordinateFirst.equals(coordinateSecond);

        Assertions.assertTrue(actualResult);
    }

    @Test
    public void EqualCoords_Coordinates_NotEquals() {
        Coordinate coordinateFirst = new Coordinate(3, 4);
        Coordinate coordinateSecond = new Coordinate(3, 5);

        boolean actualResult = coordinateFirst.equals(coordinateSecond);

        Assertions.assertFalse(actualResult);
    }

    @Test
    public void EqualCoords_Coordinate_Equals() {
        Coordinate coordinate = new Coordinate(3, 4);
        int hash = coordinate.hashCode();
        boolean actualResult = coordinate.equals(coordinate);

        Assertions.assertTrue(actualResult);
    }

}
