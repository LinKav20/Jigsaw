package com.flinkou.demo.core.services;

import com.flinkou.demo.props.GameSettings;
import com.flinkou.demo.core.controllers.FigureController;
import com.flinkou.demo.core.models.Coordinate;
import com.flinkou.demo.core.models.Field;
import com.flinkou.demo.core.models.Figure;

import java.util.ArrayList;

public class GameLogic {
    // Filed of game.
    private Field field;
    // Figure controller for figures.
    private FigureController figureController;
    // Score of the game.
    private int score;
    // Maximum game time in seconds.
    private int maxGameTimeInSec;
    // Names of all players.
    private String[] names;

    /**
     * Default constructor.
     */
    public GameLogic() {
        maxGameTimeInSec = 3600;
        field = new Field();
        figureController = new FigureController();
        score = 0;
        names = new String[]{"Unknown"};
    }

    /**
     * Constructor with params.
     *
     * @param gameTime Maximum game time in secs.
     * @param seed     Seed for random generation figures.
     * @param names    Names of players.
     */
    public GameLogic(int gameTime, int seed, String[] names) {
        maxGameTimeInSec = gameTime;
        field = new Field();
        figureController = new FigureController(seed);
        score = 0;
        this.names = names;
    }

    public String getNames() {
        String answer = "";
        switch (names.length) {
            case 1 -> answer = "Go go go, " + names[0];
            case 2 -> answer = names[0] + " VS " + names[1];
            default ->answer = "UNKNOWN xN";
        }

        return answer;
    }

    /**
     * Get game score.
     *
     * @return Game score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Get maximum game time in seconds.
     *
     * @return Maximum game time in seconds.
     */
    public int getMaxGameTimeInSec() {
        return maxGameTimeInSec;
    }

    /**
     * Start game.
     */
    public void startGame() {
        field = new Field();
        score = 0;
    }

    /**
     * Check if it is the end of the game.
     *
     * @param time Current time of game.
     * @return True if game is over, else false.
     */
    public boolean gameOver(int time) {
        return time >= maxGameTimeInSec;
    }

    /**
     * Get figure for set.
     *
     * @return New figure.
     */
    public Figure getFigure() {
        return figureController.getFigure();
    }

    /**
     * Check can we put the figure on the field.
     *
     * @param startFieldCoordinate  Start field coordinate.
     * @param startFigureCoordinate Start figure coordinate.
     * @param figure                Figure to put.
     * @return True if figure puts on the field, else false.
     */
    public boolean canPutFigure(Coordinate startFieldCoordinate, Coordinate startFigureCoordinate, Figure figure) {
        return isCoordinateOnField(startFieldCoordinate) &&
                field.canPutFigure(startFieldCoordinate, startFigureCoordinate, figure);
    }

    /**
     * Put figure on the field.
     *
     * @param startFieldCoordinate  Start field coordinate.
     * @param startFigureCoordinate Start figure coordinate.
     * @param figure                Figure to put.
     * @return List of changed filed coordinated.
     */
    public ArrayList<Coordinate> putFigure(Coordinate startFieldCoordinate, Coordinate startFigureCoordinate, Figure figure) {
        score++;
        return field.putFigure(startFieldCoordinate, startFigureCoordinate, figure);
    }

    /**
     * Check if filed contains coordinate.
     *
     * @param coordinate Coordinate to check.
     * @return True if filed contains coordinate, else false.
     */
    private boolean isCoordinateOnField(Coordinate coordinate) {
        return coordinate.Y() < GameSettings.getYCount() &&
                coordinate.Y() >= 0 && coordinate.X() < GameSettings.getXCount() &&
                coordinate.X() >= 0;
    }
}
