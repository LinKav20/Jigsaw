package com.flinkou.demo.core.generation;

import com.flinkou.demo.core.models.Coordinate;
import com.flinkou.demo.core.models.Figure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class for creating objects.
 */
public class Creation {

    /**
     * Create all figures in game.
     *
     * @return List of figures.
     */
    public static ArrayList<Figure> createAllFigures() {
        Coordinate a1 = new Coordinate(-1, -1);
        Coordinate a2 = new Coordinate(-1, 0);
        Coordinate a3 = new Coordinate(-1, 1);
        Coordinate a4 = new Coordinate(0, -1);
        Coordinate a5 = new Coordinate(0, 0);
        Coordinate a6 = new Coordinate(0, 1);
        Coordinate a7 = new Coordinate(1, -1);
        Coordinate a8 = new Coordinate(1, 0);
        Coordinate a9 = new Coordinate(1, 1);

        ArrayList<ArrayList<Coordinate>> points = new ArrayList<>();

        // a1 a2 a3
        // a4 a5 a6
        // a7 a8 a9
        points.add(new ArrayList<>(Arrays.asList(a2, a3, a5, a8)));
        points.add(new ArrayList<>(Arrays.asList(a4, a7, a8, a9)));
        points.add(new ArrayList<>(Arrays.asList(a3, a6, a8, a9)));
        points.add(new ArrayList<>(Arrays.asList(a4, a5, a6, a9)));

        // a1 a2 a3
        // a4 a5 a6
        // a7 a8 a9
        points.add(new ArrayList<>(Arrays.asList(a1, a2, a5, a8)));
        points.add(new ArrayList<>(Arrays.asList(a3, a4, a5, a6)));
        points.add(new ArrayList<>(Arrays.asList(a2, a5, a8, a9)));
        points.add(new ArrayList<>(Arrays.asList(a4, a5, a6, a7)));

        // a1 a2 a3
        // a4 a5 a6
        // a7 a8 a9
        points.add(new ArrayList<>(Arrays.asList(a1, a4, a5, a8)));
        points.add(new ArrayList<>(Arrays.asList(a5, a6, a7, a8)));
        points.add(new ArrayList<>(Arrays.asList(a2, a4, a5, a7)));
        points.add(new ArrayList<>(Arrays.asList(a4, a5, a8, a9)));

        // a1 a2 a3
        // a4 a5 a6
        // a7 a8 a9
        points.add(new ArrayList<>(Arrays.asList(a3, a6, a7, a8, a9)));
        points.add(new ArrayList<>(Arrays.asList(a1, a4, a7, a8, a9)));
        points.add(new ArrayList<>(Arrays.asList(a1, a2, a3, a4, a7)));
        points.add(new ArrayList<>(Arrays.asList(a1, a2, a3, a6, a9)));

        // a1 a2 a3
        // a4 a5 a6
        // a7 a8 a9
        points.add(new ArrayList<>(Arrays.asList(a2, a5, a7, a8, a9)));
        points.add(new ArrayList<>(Arrays.asList(a1, a2, a3, a5, a8)));
        points.add(new ArrayList<>(Arrays.asList(a1, a4, a5, a6, a7)));
        points.add(new ArrayList<>(Arrays.asList(a3, a4, a5, a6, a9)));

        // a1 a2 a3
        // a4 a5 a6
        // a7 a8 a9
        points.add(new ArrayList<>(Arrays.asList(a4, a5, a6)));
        points.add(new ArrayList<>(Arrays.asList(a2, a5, a8)));
        points.add(new ArrayList<>(List.of(a5)));

        // a1 a2 a3
        // a4 a5 a6
        // a7 a8 a9
        points.add(new ArrayList<>(Arrays.asList(a1, a2, a4)));
        points.add(new ArrayList<>(Arrays.asList(a2, a3, a6)));
        points.add(new ArrayList<>(Arrays.asList(a3, a5, a6)));
        points.add(new ArrayList<>(Arrays.asList(a1, a4, a5)));

        // a1 a2 a3
        // a4 a5 a6
        // a7 a8 a9
        points.add(new ArrayList<>(Arrays.asList(a2, a5, a6, a8)));
        points.add(new ArrayList<>(Arrays.asList(a4, a5, a6, a8)));
        points.add(new ArrayList<>(Arrays.asList(a2, a4, a5, a8)));
        points.add(new ArrayList<>(Arrays.asList(a2, a4, a5, a6)));

        ArrayList<Figure> figures = new ArrayList<>();

        for (ArrayList<Coordinate> point : points) {
            figures.add(new Figure(point));
        }

        return figures;
    }
}
