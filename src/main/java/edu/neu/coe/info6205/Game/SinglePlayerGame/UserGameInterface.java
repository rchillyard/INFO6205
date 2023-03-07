package edu.neu.coe.info6205.Game.SinglePlayerGame;

import edu.neu.coe.info6205.Game.Move;

public interface UserGameInterface<T> {

    public void fill();

    public boolean validateMove(Move move);

    public T[][] getGrid();
}
