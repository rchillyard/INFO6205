package edu.neu.coe.info6205.Game;

public interface IGame {
    /**
     * to make the next move
     */
    public void move();
    public void displayGame();
    public boolean isGameOver();
    public Player getWinner();

    public Player checkWinner();

}
