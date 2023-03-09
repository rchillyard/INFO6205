package edu.neu.coe.info6205.Game.SinglePlayerGame.Games.Sudoku;

import edu.neu.coe.info6205.Game.Move;
import edu.neu.coe.info6205.Game.SinglePlayerGame.UserGame;
import edu.neu.coe.info6205.Game.Solver;
import edu.neu.coe.info6205.Game.SolverType;

import java.util.ArrayList;
import java.util.HashSet;

public class SudokuSolver2 implements Solver<Integer, UserGame<Integer>> {

    private static final SolverType type = SolverType.SingleTurnSolver;

    private UserGame<Integer> game;
    private HashSet<Integer>[] rowFills;
    private HashSet<Integer>[] colFills;
    private HashSet<Integer>[][] gridFills;

    SudokuSolver2(UserGame<Integer> game, int size) {
        this.game = game;
        rowFills = new HashSet[size * size];
        colFills = new HashSet[size * size];
        gridFills = new HashSet[size][size];
    }

    @Override
    public void solve(UserGame<Integer> game) {
        game.display();
    }

    public void recurse() {

    }

    @Override
    public Move<Integer> getMove(Integer[][] grid) {
        return null;
    }

    @Override
    public SolverType getType() {
        return null;
    }
}
