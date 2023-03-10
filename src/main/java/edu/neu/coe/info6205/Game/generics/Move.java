package edu.neu.coe.info6205.Game.generics;

import java.util.function.UnaryOperator;

public interface Move<StateType, Position> {

    UnaryOperator<StateType> stateTransition();

    Position startPosition();

    Position endPosition();

    Move<StateType, Position> next();
}
