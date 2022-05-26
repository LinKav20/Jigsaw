package com.flinkou.demo.core.models;

import com.flinkou.demo.enums.CellState;

/**
 * Entity for cell.
 */
public class Cell {
    // Coordinate of cell.
    Coordinate coords;
    // State of cell.
    CellState state;

    /**
     * Default constructor.
     *
     * @param coords Coords of cell.
     */
    public Cell(Coordinate coords) {
        this.coords = coords;
        state = CellState.EMPTY;
    }

    /**
     * Set new cell state.
     *
     * @param state New cell state.
     */
    public void setState(CellState state) {
        this.state = state;
    }

    /**
     * Get cell state.
     *
     * @return Current cell state.
     */
    public CellState getState() {
        return state;
    }

    /**
     * Get cell coordinate.
     *
     * @return Cell coordinate.
     */
    public Coordinate getCoords() {
        return coords;
    }
}
