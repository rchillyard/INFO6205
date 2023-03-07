package edu.neu.coe.info6205.Game.SinglePlayerGame.Games.Sudoku;

import edu.neu.coe.info6205.Game.SinglePlayerGame.SPGameCreator;

public class SudokuGameCreator extends SPGameCreator<Integer> {

    @Override
    protected Integer[][] createGame(int row, int column) {
        return new Integer[0][column];
    }

    @Override
    protected Integer[][] createPlayerGameView() {
        return new Integer[0][];
    }
}
