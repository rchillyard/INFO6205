package edu.neu.coe.info6205.Game.SinglePlayerGame.Games.Sudoku;

import edu.neu.coe.info6205.Game.Move;
import edu.neu.coe.info6205.Game.Solver;
import edu.neu.coe.info6205.Game.Player;
import edu.neu.coe.info6205.Game.SinglePlayerGame.SPGameCreator;
import edu.neu.coe.info6205.Game.SinglePlayerGame.SinglePlayerGame;

public class SudokuGame extends SinglePlayerGame<Integer> {

    private int maxMoves = 0;

    public SudokuGame(SPGameCreator<Integer> gameCreator, boolean isBot,
                      Solver<Integer, SinglePlayerGame<Integer>> moveGenerator, boolean singleTurnGameCompletion) {
        super(gameCreator, 9, 9, isBot, moveGenerator, singleTurnGameCompletion);
    }
    /**
     *
     */
    @Override
    public void displayGame() {
    }

    @Override
    public boolean isGameOver() {
        return maxMoves == getTotalMovesTillNow();
    }

    @Override
    public Player getWinner() {
        return null;
    }


    @Override
    public Player checkWinner() {
        return null;
    }


    @Override
    public void fill() {

    }

    @Override
    public boolean validateMove(Move move) {
        return false;
    }
}
