package edu.neu.coe.info6205.game;

import edu.neu.coe.info6205.game.generics.Board;
import edu.neu.coe.info6205.game.generics.GridPosition;
import edu.neu.coe.info6205.game.generics.StateTransition;
import edu.neu.coe.info6205.game.singlePlayerGame.Games.Sudoku.Sudoku;
import edu.neu.coe.info6205.game.singlePlayerGame.Games.Sudoku.SudokuCreator;
import edu.neu.coe.info6205.game.singlePlayerGame.Games.Sudoku.SudokuSolver;
import edu.neu.coe.info6205.game.singlePlayerGame.UserGame;

public class Runner {

    public static void main(String[] args) {


        Sudoku game = new Sudoku(
                new SudokuCreator(), true, new SudokuSolver(3), 3);
        game.display();
        game.run();
        game.checkWinner();
        game.display();
        // TODO check this assignment
        Player<Integer, UserGame<Board<Integer, GridPosition, StateTransition<Integer, GridPosition>>, Integer>> winner = game.getWinner();
        System.out.println(winner.getTotalTime()/Math.pow(10, 9));
        System.out.println(winner.getAverageTimePerMove()/Math.pow(10, 9));

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
