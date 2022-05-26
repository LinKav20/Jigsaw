package com.flinkou.demo.models;

import com.flinkou.demo.core.models.Coordinate;
import com.flinkou.demo.core.models.Figure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class FigureTests {

    @Test
    public void ContainCoordinate_Figure_Contains(){
        Figure figure = new Figure(new ArrayList<>(Arrays.asList(
                new Coordinate(0, 0),
                new Coordinate(1, 0))));

        boolean actualResult = figure.contain(new Coordinate(0 ,0));

        Assertions.assertTrue(actualResult);
    }

    @Test
    public void ContainCoordinate_Figure_NotContains(){
        Figure figure = new Figure(new ArrayList<>(Arrays.asList(
                new Coordinate(0, 0),
                new Coordinate(1, 0))));

        boolean actualResult = figure.contain(new Coordinate(3 ,0));

        Assertions.assertFalse(actualResult);
    }
}
