package com.flinkou.demo.models;

import com.flinkou.demo.enums.CellState;
import com.flinkou.demo.core.models.Cell;
import com.flinkou.demo.core.models.Coordinate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CellTests {

    @Test
    public void GetState_Cell_Equals(){
        Cell cell = new Cell(new Coordinate(0 ,0));

        CellState actualState = cell.getState();
        CellState expectedState = CellState.EMPTY;

        Assertions.assertEquals(expectedState, actualState);
    }

    @Test
    public void SetState_Cell_Equals(){
        Cell cell = new Cell(new Coordinate(0 ,0));
        cell.setState(CellState.FILL);

        CellState actualState = cell.getState();
        CellState expectedState = CellState.FILL;

        Assertions.assertEquals(expectedState, actualState);
    }

    @Test
    public void GetCoords_Cell_Equals(){
        Coordinate coordinate = new Coordinate(0 ,0);
        Cell cell = new Cell(coordinate);

        Coordinate actualCoords = cell.getCoords();
        Coordinate expectedCoords = coordinate;

        Assertions.assertEquals(expectedCoords, actualCoords);
    }
}
