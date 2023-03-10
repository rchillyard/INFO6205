package edu.neu.coe.info6205.Game;

import edu.neu.coe.info6205.Game.SinglePlayerGame.Games.Sudoku.SudokuGame;
import edu.neu.coe.info6205.Game.SinglePlayerGame.Games.Sudoku.SudokuGameCreator;
import edu.neu.coe.info6205.Game.SinglePlayerGame.Games.Sudoku.SudokuSolver;
import edu.neu.coe.info6205.Game.SinglePlayerGame.Games.Sudoku.SudokuSolver2;

public class Runner {

    public static void main(String[] args) {


        SudokuGame game = new SudokuGame(new SudokuGameCreator(), true, new SudokuSolver());
        game.display();
        /*System.out.println(game.getGrid().hashCode());
        game.fillWrapper(new Move<>(0, 0, 3));
        System.out.println("after adding 3 - " + getHash(game.getGrid()));
        game.fillWrapper(new Move<>(0, 0, 4));
        System.out.println("after adding 4 - " + getHash(game.getGrid()));
        game.fillWrapper(new Move<>(0, 0, 3));
        System.out.println("after adding 3 - " + getHash(game.getGrid()));

         */
        game.run();
        game.checkWinner();
        game.display();
        Player winner = game.getWinner();
        System.out.println(winner.getTotalTime());
        System.out.println(winner.getAverageTimePerMove());

    }

    private static int getHash(Integer[][] grid) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(grid[i][j] != null ? grid[i][j] : "_");
            }
        }
        return sb.toString().hashCode();
    }
}
