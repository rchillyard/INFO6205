package edu.neu.coe.info6205.game.generics;

import java.util.Arrays;

public class Board_Grid<StateType> implements Board<StateType, GridPosition, StateTransition<StateType, GridPosition>> {
    public Board_Grid(Object[][] grid) {
        this.grid = grid;
    }

    public Board_Grid(int rows, int columns) {
        this.grid = new Object[rows][columns];
    }

    @Override
    public StateType getState(GridPosition gridPosition) {
        if (gridPosition != null)
            // TODO this cast is OK -- it's because we have to store the grid as an array of Object
            //noinspection unchecked
            return (StateType) grid[gridPosition.x][gridPosition.y];
        else return null;
    }

    /**
     * Method to generate a new Board from <code>this</code>> Board, by applying the transitions defined by the given <code>move</code>>.
     *
     * @param move a move in the current game.
     * @return a new Board based on <code>this</code> board and the effects of <code>move</code>.
     */
    @Override
    public Board<StateType, GridPosition, StateTransition<StateType, GridPosition>> move(StateTransition<StateType, GridPosition> move) {
        Object[][] newGrid = Arrays.copyOf(grid, grid.length); // TODO check this
        Board_Grid<StateType> newBoard = new Board_Grid<>(newGrid);
        StateTransition<StateType, GridPosition> current = move;
        while (current != null) {
            // NOTE: the endPosition is not necessarily different from the startPosition.
            // If the two positions are the same, then the state must be different.
            GridPosition endPosition = current.endPosition();
            // TODO create a method to update the state of the board at some position.
            newBoard.grid[endPosition.x][endPosition.y] = current.transitionFunction().apply(getState(current.startPosition()));
            current = current.next();
        }
        return newBoard;
    }

//    @Override
//    public Board<StateType, GridPosition, StateTransition> move(StateTransition move) {
//        Object[][] newGrid = Arrays.copyOf(grid,grid.length); // TODO check this
//        Board_Grid<StateType, StateTransition> newBoard = new Board_Grid<>(newGrid);
//        StateTransition current = move;
//        while (current != null) {
//
//        }
//        return null;
//    }

    //
//    @Override
//    public Board<T, GridPosition, StateTransition<T>> move(StateTransition<T> move) {
//        Object[][] newGrid = Arrays.copyOf(grid,grid.length); // TODO check this
//        Board_Grid<T> newBoard = new Board_Grid<>(newGrid);
//        newBoard.move(move);
//        return newBoard;
//    }

    // This is essentially a StateType[][]. It's just that Java doesn't really support that concept.
    protected final Object[][] grid;
}
