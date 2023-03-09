package edu.neu.coe.info6205.Game;

import edu.neu.coe.info6205.Game.SinglePlayerGame.Games.Sudoku.SudokuGame;
import edu.neu.coe.info6205.Game.SinglePlayerGame.Games.Sudoku.SudokuGameCreator;
import edu.neu.coe.info6205.Game.SinglePlayerGame.Games.Sudoku.SudokuSolver;
import edu.neu.coe.info6205.Game.SinglePlayerGame.Games.Sudoku.SudokuSolver2;

public class Runner {

    public static void main(String[] args) {


        SudokuGame game = new SudokuGame(new SudokuGameCreator(), true, new SudokuSolver());
        game.display();
        game.run();
        game.display();
    }
}
