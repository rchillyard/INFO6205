package edu.neu.coe.info6205.Game;


public interface Solver<T, E> {

    public void solve(E game);

    public Move<T> getMove(T[][] grid);

    public SolverType getType();

}
