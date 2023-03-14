package edu.neu.coe.info6205.game;

public interface Game {
    /**
     * to make the next move
     */
    public Move move();
    public void display();
    public boolean isGameOver();
    public Player getWinner();

    public Player checkWinner();

}
