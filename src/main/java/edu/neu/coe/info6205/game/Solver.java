package edu.neu.coe.info6205.game;


public interface Solver<T, G> {

    void solve(G game);

    /**
     * TODO this always returns null.
     *
     * @param grid the grid.
     * @return a StateTransition.
     */
    Move<T> getMove(T[][] grid);

    SolverType getType();

}
