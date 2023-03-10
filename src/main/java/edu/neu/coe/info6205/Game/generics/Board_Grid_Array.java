package edu.neu.coe.info6205.Game.generics;

import java.util.function.UnaryOperator;

public class Board_Grid_Array<T> extends Board_Grid<T> {
    public Board_Grid_Array(Object[][] grid) {
        super(grid);
    }

    public Board_Grid_Array(int rows, int columns) {
        super(rows, columns);
    }

    public T getState(int row, int column) {
        GridPosition gridPosition = new GridPosition(column, row);
        return super.getState(gridPosition);
    }

    public Board<T, GridPosition, Move<T, GridPosition>> move(edu.neu.coe.info6205.Game.Move<T> move) {
        int x = move.getColumn();
        int y = move.getRow();
        T val = move.getVal();
        Move<T, GridPosition> m = new Move<T, GridPosition>() {
            @Override
            public UnaryOperator<T> stateTransition() {
                return t -> val;
            }

            @Override
            public GridPosition startPosition() {
                return null;
            }

            @Override
            public GridPosition endPosition() {
                return new GridPosition(x, y);
            }

            @Override
            public Move<T, GridPosition> next() {
                return null;
            }
        };
        return super.move(m);
    }
}
