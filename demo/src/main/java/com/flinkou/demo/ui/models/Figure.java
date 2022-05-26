package com.flinkou.demo.ui.models;

import com.flinkou.demo.props.Draggable;
import com.flinkou.demo.props.GameSettings;
import com.flinkou.demo.enums.FigureState;
import com.flinkou.demo.core.models.Coordinate;
import javafx.scene.layout.Pane;

/**
 * Entity of visual figure.
 */
public class Figure extends Pane {
    // State of figure on field.
    private FigureState figureState = FigureState.ON_SPACE;
    // Field coordinates to put figure.
    private Coordinate filedCoordinateToPut = new Coordinate(-100, -100);
    // Figure coordinate to put on field.
    private Coordinate figureCoordinateToPut = new Coordinate(-100, -100);

    /**
     * Constructor with figure.
     *
     * @param figure General figure to create visual.
     */
    public Figure(com.flinkou.demo.core.models.Figure figure) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (figure.contain(new Coordinate(i - 1, j - 1))) {
                    getChildren().add(createTile(i - 1, j - 1));
                }
            }
        }

        setStartPosition();
    }

    /**
     * Get figure state.
     *
     * @return Figure state.
     */
    public FigureState getFigureState() {
        return figureState;
    }

    /**
     * Make figure draggable.
     */
    public void makeDraggable() {
        Draggable draggable = new Draggable();
        draggable.makeDraggable(this);
    }

    public void disableDraggable(){
        Draggable draggable = new Draggable();
        draggable.disableDraggable(this);
    }

    /**
     * Set start position of figure.
     */
    public void setStartPosition() {
        this.setLayoutX(GameSettings.getFigurePositionX() * GameSettings.getXSize());
        this.setLayoutY(GameSettings.getFigurePositionY() * GameSettings.getYSize());
    }

    /**
     * Get field coordinate to put.
     *
     * @return Field coordinates to put.
     */
    public Coordinate getFiledCoordinateToPut() {
        return filedCoordinateToPut;
    }

    /**
     * Get figure coordinate to put.
     *
     * @return Figure coordinate to put.
     */
    public Coordinate getFigureCoordinateToPut() {
        return figureCoordinateToPut;
    }

    /**
     * Set figure state.
     *
     * @param fs Figure state.
     */
    public void setFigureState(FigureState fs) {
        figureState = fs;
    }

    /**
     * Create tile for figure.
     *
     * @param i I coordinate.
     * @param j J coordinate.
     * @return Tile of figure.
     */
    private Tile createTile(int i, int j) {
        Tile tile = new Tile();

        tile.setId(i + ";" + j);
        tile.setTranslateX((i) * GameSettings.getXSize());
        tile.setTranslateY((j) * GameSettings.getYSize());

        tile.changeColor(GameSettings.getCellFigureColor());
        tile.setStroke(GameSettings.getStrokeColor());

        addMouseClickedEvent(tile);

        return tile;
    }

    /**
     * Mouse clicked event for figure.
     *
     * @param tile Tile to set event.
     */
    private void addMouseClickedEvent(Tile tile) {
        tile.setOnMouseReleased(event -> {
            filedCoordinateToPut = convertToCoordinateField(event.getSceneX(), event.getSceneY());
            figureCoordinateToPut = createCoordsFromTileId(tile.getId());
            figureState = FigureState.TRY_TO_PUT;
        });
    }

    /**
     * Create coordinates from tile id.
     *
     * @param tileID Tile id.
     * @return New coordinate.
     */
    private Coordinate createCoordsFromTileId(String tileID) {
        String[] dots = tileID.split(";");

        if (dots.length != 2) {
            throw new RuntimeException("Cannot convert tile id to coordinate.");
        }

        int x = convertStringToInt(dots[0]);
        int y = convertStringToInt(dots[1]);

        return new Coordinate(x, y);
    }

    /**
     * Save converter string to int.
     *
     * @param num String to convert.
     * @return Int num.
     */
    private int convertStringToInt(String num) {
        try {
            int number = Integer.parseInt(num);
            return number;
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    /**
     * Convert ints to coordinate.
     *
     * @param x X coordinate.
     * @param y Y coordinate.
     * @return New coordinate.
     */
    private Coordinate convertToCoordinateField(double x, double y) {
        return new Coordinate((int) (x / GameSettings.getXSize()), (int) (y / GameSettings.getYSize()));
    }
}

