package edu.neu.coe.info6205.Game;

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
