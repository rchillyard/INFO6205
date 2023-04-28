package edu.neu.coe.info6205.game;


public interface Solver<T, E> {

    void solve(E game);

    /**
     * TODO this always returns null.
     *
     * @param grid  the grid.
     * @return a Move.
     */
    Move<T> getMove(T[][] grid);

    SolverType getType();

}
