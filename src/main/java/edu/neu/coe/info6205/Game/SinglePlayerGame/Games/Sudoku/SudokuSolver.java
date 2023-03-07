package edu.neu.coe.info6205.Game.SinglePlayerGame.Games.Sudoku;

import edu.neu.coe.info6205.Game.Move;
import edu.neu.coe.info6205.Game.SinglePlayerGame.UserGameInterface;
import edu.neu.coe.info6205.Game.Solver;
import edu.neu.coe.info6205.Game.SolverType;

import java.util.List;

public class SudokuSolver implements Solver<Integer, SudokuGame> {

    private static final SolverType type = SolverType.SingleTurnSolver;

    @Override
    public Move<Integer> solve(UserGameInterface game) {
        return null;
    }

    @Override
    public SolverType getType() {
        return type;
    }


}
