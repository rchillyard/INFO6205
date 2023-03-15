package edu.neu.coe.info6205.game;

public interface Game<T, G> {
    /**
     * to make the next move
     *
     * TODO this always returns null.
     */
    Move<T> move();
    void display();
    boolean isGameOver();
    Player<T, G> getWinner();

    Player<T, G> checkWinner();

}
