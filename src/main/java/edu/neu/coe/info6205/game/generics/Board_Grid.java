package edu.neu.coe.info6205.game.generics;

import java.util.Arrays;

public class Board_Grid<StateType> implements Board<StateType, GridPosition, MoveProcessor<StateType, GridPosition>> {
    public Board_Grid(Object[][] grid) {
        this.grid = grid;
    }

    public Board_Grid(int rows, int columns) {
        this.grid = new Object[rows][columns];
    }

    @Override
    public StateType getState(GridPosition gridPosition) {
        // TODO this cast is OK -- it's because we have to store the grid as an array of Object
        return (StateType) grid[gridPosition.x][gridPosition.y];
    }

    @Override
    public Board<StateType, GridPosition, MoveProcessor<StateType, GridPosition>> move(MoveProcessor<StateType, GridPosition> move) {
        Object[][] newGrid = Arrays.copyOf(grid, grid.length); // TODO check this
        Board_Grid<StateType> newBoard = new Board_Grid<>(newGrid);
        MoveProcessor<StateType, GridPosition> current = move;
        // TODO need to update current in loop.
        while (current != null) {
            StateType stateType = getState(current.startPosition());
            StateType state = current.stateTransition().apply(stateType);
            GridPosition endPosition = current.endPosition();
            newBoard.grid[endPosition.x][endPosition.y] = state;
        }
        return newBoard;
    }

//    @Override
//    public Board<StateType, GridPosition, MoveProcessor> move(MoveProcessor move) {
//        Object[][] newGrid = Arrays.copyOf(grid,grid.length); // TODO check this
//        Board_Grid<StateType, MoveProcessor> newBoard = new Board_Grid<>(newGrid);
//        MoveProcessor current = move;
//        while (current != null) {
//
//        }
//        return null;
//    }

    //
//    @Override
//    public Board<T, GridPosition, MoveProcessor<T>> move(MoveProcessor<T> move) {
//        Object[][] newGrid = Arrays.copyOf(grid,grid.length); // TODO check this
//        Board_Grid<T> newBoard = new Board_Grid<>(newGrid);
//        newBoard.move(move);
//        return newBoard;
//    }

    protected final Object[][] grid;
}
