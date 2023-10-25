package edu.neu.coe.info6205.game.singlePlayerGame.Games.Sudoku;

import edu.neu.coe.info6205.game.generics.GridPosition;
import edu.neu.coe.info6205.game.generics.StateTransition;

import java.util.function.UnaryOperator;

public class SudokuStateTransition implements StateTransition<Integer, GridPosition> {

    final GridPosition gridPosition;
    final Integer value;

    SudokuStateTransition(int x, int y, Integer value) {
        this.gridPosition = new GridPosition(x, y);
        this.value = value;
    }

    @Override
    public UnaryOperator<Integer> transitionFunction() {
        return t -> this.value;
    }

    @Override
    public GridPosition startPosition() {
        return this.gridPosition;
    }

    @Override
    public GridPosition endPosition() {
        return this.gridPosition;
    }

    @Override
    public StateTransition<Integer, GridPosition> next() {
        return null;
    }
}
