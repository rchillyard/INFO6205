package edu.neu.coe.info6205.game.generics;

import java.util.function.UnaryOperator;

public interface Move<StateType, Position> {

    UnaryOperator<StateType> stateTransition();

    /**
     * TODO this always returns null.
     *
     * @return the start position.
     */
    Position startPosition();

    Position endPosition();

    /**
     * TODO this always returns null.
     *
     * @return the next component of this Move.
     */
    Move<StateType, Position> next();
}
