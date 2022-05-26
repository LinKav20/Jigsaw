package com.flinkou.demo.ui.models;

import com.flinkou.demo.props.GameSettings;
import com.flinkou.demo.core.models.Coordinate;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Entity of visual field.
 */
public class Field extends Pane {

    // All cells in field.
    private int amountOfCells = 0;

    /**
     * Default constructor.
     */
    public Field() {
        setPrefSize(GameSettings.getHeight() * 2, GameSettings.getWidth());

        for (int i = 0; i < GameSettings.getXCount(); ++i) {
            for (int j = 0; j < GameSettings.getYCount(); ++j) {
                Tile tile = new Tile();
                tile.setId(i + ";" + j);
                tile.setStroke(GameSettings.getStrokeColor());
                tile.setTranslateX(i * GameSettings.getXSize());
                tile.setTranslateY(j * GameSettings.getYSize());
                getChildren().add(tile);
            }
        }

        amountOfCells = GameSettings.getXCount() * GameSettings.getYCount();
    }

    /**
     * Change color needed coordinates.
     *
     * @param figureCoordinates Coordinates to change color.
     */
    public void changeColor(ArrayList<Coordinate> figureCoordinates) {
        for (Coordinate figureCoordinate : figureCoordinates) {
            int position = coordinateToLineFormat(figureCoordinate);
            Tile tile = (Tile) (getChildren().get(position));
            tile.changeColor(GameSettings.getCellFieldColor());
        }
    }

    /**
     * Make from two-params coordinate one-param coordinate.
     *
     * @param coordinate Coordinate to check format.
     * @return One-param coordinate.
     */
    public int coordinateToLineFormat(Coordinate coordinate) {
        return coordinate.X() * GameSettings.getXCount() + coordinate.Y();
    }

    /**
     * Clear field.
     */
    public void clear() {
        for (int i = 0; i < amountOfCells; ++i) {
            Tile tile = (Tile) (getChildren().get(i));
            tile.changeColor(GameSettings.getClearCellColor());
        }
    }
}
