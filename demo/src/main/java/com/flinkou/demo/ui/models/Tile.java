package com.flinkou.demo.ui.models;

import com.flinkou.demo.props.GameSettings;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Entity of visual cell.
 */
public class Tile extends StackPane {
    // Rectangle for creating cell.
    Rectangle tile;

    /**
     * Default constructor.
     */
    public Tile() {
        tile = new Rectangle(GameSettings.getXSize(), GameSettings.getYSize());
        tile.setFill(null);

        getChildren().add(tile);
    }

    /**
     * Set stroke on tile.
     *
     * @param color Color of stroke.
     */
    public void setStroke(Color color) {
        tile.setStroke(color);
    }

    /**
     * Change color of tile.
     *
     * @param color Color of tile.
     */
    public void changeColor(Color color) {
        tile.setFill(color);
    }
}
