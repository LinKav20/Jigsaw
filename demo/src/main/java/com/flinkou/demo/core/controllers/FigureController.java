package com.flinkou.demo.core.controllers;

import com.flinkou.demo.core.generation.Creation;
import com.flinkou.demo.core.models.Figure;

import java.text.DateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class to control all figures.
 */
public class FigureController {
    Random random;
    // All figures in game.
    ArrayList<Figure> figures;

    /**
     * Default constructor.
     */
    public FigureController() {
        figures = Creation.createAllFigures();
        random = new Random(4);
    }

    /**
     * Constructor with seed.
     *
     * @param seed Seed for random.
     */
    public FigureController(int seed) {
        figures = Creation.createAllFigures();
        random = new Random(seed);
    }

    /**
     * Get random figure from figures.
     *
     * @return Random figure.
     */
    public Figure getFigure() {
        int index = random.nextInt(figures.size());
        return figures.get(index);
    }
}
