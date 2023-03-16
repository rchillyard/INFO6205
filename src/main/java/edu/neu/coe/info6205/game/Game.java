package edu.neu.coe.info6205.game;

public interface Game {
    /**
     * to make the next move
     *
     * TODO this always returns null.
     */
    Move move();
    void display();
    boolean isGameOver();
    Player getWinner();

    Player checkWinner();

}
