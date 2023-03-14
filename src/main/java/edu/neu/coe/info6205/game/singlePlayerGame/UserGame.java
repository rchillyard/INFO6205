package edu.neu.coe.info6205.game.singlePlayerGame;

import edu.neu.coe.info6205.game.Move;
import edu.neu.coe.info6205.game.generics.Board;

public interface UserGame<Board, T> {

    public boolean fillWrapper(Move<T> move);

    public boolean validateMove(Move move);

    public boolean isGameOver();

    public Board getBoard();

    public void display();
}
