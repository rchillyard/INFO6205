package edu.neu.coe.info6205.game.generics;

import java.util.function.UnaryOperator;

public interface Move<StateType, Position> {

    UnaryOperator<StateType> stateTransition();

    Position startPosition();

    Position endPosition();

    Move<StateType, Position> next();
}
