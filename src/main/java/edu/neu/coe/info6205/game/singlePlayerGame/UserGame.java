package edu.neu.coe.info6205.game.singlePlayerGame;

import edu.neu.coe.info6205.game.Move;

public interface UserGame<Board, T> {

    boolean fillWrapper(Move<T> move);

    /**
     * TODO this always returns false.
     *
     * @param move the StateTransition to be validated.
     * @return true if valid.
     */
    boolean validateMove(Move<T> move);

    boolean isGameOver();

    Board getBoard();

    void display();
}
