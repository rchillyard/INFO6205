package edu.neu.coe.info6205.Game.SinglePlayerGame;

import edu.neu.coe.info6205.Game.Move;

public interface UserGame<T> {

    public boolean fillWrapper(Move<T> move);

    public boolean validateMove(Move move);

    public boolean isGameOver();

    public T[][] getGrid();

    public void display();
}
