package edu.neu.coe.info6205.Game;


import edu.neu.coe.info6205.Game.SinglePlayerGame.UserGameInterface;

import java.util.List;

public interface Solver<T, E> {

    public Move<T> solve(UserGameInterface game);

    public SolverType getType();

}
