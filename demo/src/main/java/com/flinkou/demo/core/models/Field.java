package com.flinkou.demo.core.models;

import com.flinkou.demo.props.GameSettings;
import com.flinkou.demo.enums.CellState;

import java.util.ArrayList;

/**
 * Entity of field.
 */
public class Field {
    // Cells to make field.
    private final Cell[][] field;

    /**
     * Default constructor.
     */
    public Field() {
        field = new Cell[GameSettings.getXCount()][GameSettings.getYCount()];

        for (int i = 0; i < GameSettings.getXCount(); ++i) {
            for (int j = 0; j < GameSettings.getYCount(); ++j) {
                field[i][j] = new Cell(new Coordinate(i, j));
            }
        }
    }

    /**
     * Check can this figure be in the field.
     *
     * @param startFieldCoordinate  Position of field.
     * @param startFigureCoordinate Position on figure.
     * @param figure                Figure type.
     * @return True if we can put the figure on the field, else false.
     */
    public boolean canPutFigure(Coordinate startFieldCoordinate, Coordinate startFigureCoordinate, Figure figure) {
        int difX = startFieldCoordinate.X() - startFigureCoordinate.X();
        int difY = startFieldCoordinate.Y() - startFigureCoordinate.Y();

        for (Coordinate coords : figure.getCoords()) {
            Coordinate figureCellOnField = new Coordinate(coords.X() + difX, coords.Y() + difY);
            if (!containCoordinate(figureCellOnField) || !(stateOnPlace(figureCellOnField) == CellState.EMPTY)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Put figure on the field.
     *
     * @param startFieldCoordinate  Position of field.
     * @param startFigureCoordinate Position on figure.
     * @param figure                Figure type.
     * @return List of changed cells.
     */
    public ArrayList<Coordinate> putFigure(Coordinate startFieldCoordinate, Coordinate startFigureCoordinate, Figure figure) {
        int difX = startFieldCoordinate.X() - startFigureCoordinate.X();
        int difY = startFieldCoordinate.Y() - startFigureCoordinate.Y();

        ArrayList<Coordinate> changes = new ArrayList<>();

        for (Coordinate coords : figure.getCoords()) {
            Coordinate figureCellOnField = new Coordinate(coords.X() + difX, coords.Y() + difY);

            try {
                setStateOnCell(figureCellOnField);
                changes.add(figureCellOnField);
            } catch (Exception e) {
                throw new RuntimeException("Can't change cell state on (" + figureCellOnField.X() + ";" + figureCellOnField.Y() + ").");
            }
        }

        return changes;
    }

    /**
     * Check if coordinate is on field.
     *
     * @param coordinate Coordinate.
     * @return True if coordinate is on field, else false.
     */
    private boolean containCoordinate(Coordinate coordinate) {
        if (coordinate == null) {
            throw new RuntimeException("Exception: Coordinate is null.");
        }

        return coordinate.X() < GameSettings.getXCount() && coordinate.X() >= 0 &&
                coordinate.Y() < GameSettings.getYCount() && coordinate.Y() >= 0;
    }

    /**
     * Get cell state.
     *
     * @param coordinate Coordinate of cell.
     * @return State of cell.
     */
    private CellState stateOnPlace(Coordinate coordinate) {
        if (coordinate == null) {
            throw new RuntimeException("Exception: Coordinate is null.");
        }

        return field[coordinate.X()][coordinate.Y()].state;
    }

    /**
     * Set cell state.
     *
     * @param coordinate Coordinate of cell.
     */
    private void setStateOnCell(Coordinate coordinate) {
        if (coordinate == null) {
            throw new RuntimeException("Exception: Coordinate is null.");
        }

        field[coordinate.X()][coordinate.Y()].state = CellState.FILL;
    }
}
