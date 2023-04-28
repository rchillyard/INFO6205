package edu.neu.coe.info6205.game.generics;

import java.util.function.UnaryOperator;

public class Board_Grid_Array<T> extends Board_Grid<T> {
    public Board_Grid_Array(Object[][] grid) {
        super(grid);
    }

    public Board_Grid_Array(int rows, int columns) {
        super(rows, columns);
    }

    public T getState(int row, int column) {
        GridPosition gridPosition = new GridPosition(row, column);
        return super.getState(gridPosition);
    }

    public Board<T, GridPosition, StateTransition<T, GridPosition>> move(edu.neu.coe.info6205.game.Move<T> move) {
        int x = move.getRow();
        int y = move.getColumn();
        T val = move.getVal();
        StateTransition<T, GridPosition> m = new StateTransition<>() {
            @Override
            public UnaryOperator<T> transitionFunction() {
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
            public StateTransition<T, GridPosition> next() {
                return null;
            }
        };

        return super.move(m);
    }

    public int getHash() {
        StringBuilder sb = new StringBuilder();
        int n = grid.length;
        // CONSIDER is row the right name for this?
        for (Object[] row : grid) {
            for (int j = 0; j < n; j++) {
                sb.append(row[j] != null ? row[j] : "_");
            }
        }
        return sb.toString().hashCode();
    }
}
