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

    public Board<T, GridPosition, MoveProcessor<T, GridPosition>> move(edu.neu.coe.info6205.game.Move<T> move) {
        int x = move.getRow();
        int y = move.getColumn();
        T val = move.getVal();
        MoveProcessor<T, GridPosition> m = new MoveProcessor<>() {
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
            public MoveProcessor<T, GridPosition> next() {
                return null;
            }
        };

        return super.move(m);
    }

    public int getHash() {
        StringBuilder sb = new StringBuilder();
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(grid[i][j] != null ? grid[i][j] : "_");
            }
        }
        return sb.toString().hashCode();
    }
}
