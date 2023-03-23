package edu.neu.coe.info6205.game.singlePlayerGame.Games.Sudoku;

import edu.neu.coe.info6205.game.generics.GridPosition;
import edu.neu.coe.info6205.game.generics.MoveProcessor;

import java.util.function.UnaryOperator;

public class SudokuMoveProcessor implements MoveProcessor<Integer, GridPosition> {

    GridPosition gridPosition;
    Integer value;

    SudokuMoveProcessor(int x, int y, Integer value) {
        this.gridPosition = new GridPosition(x, y);
        this.value = value;
    }
    @Override
    public UnaryOperator<Integer> stateTransition() {
        return t -> this.value;
    }

    @Override
    public GridPosition startPosition() {
        return null;
    }

    @Override
    public GridPosition endPosition() {
        return this.gridPosition;
    }

    @Override
    public MoveProcessor<Integer, GridPosition> next() {
        return null;
    }
}
