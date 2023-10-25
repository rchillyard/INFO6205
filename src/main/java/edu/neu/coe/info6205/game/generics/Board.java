package edu.neu.coe.info6205.game.generics;

public interface Board<StateType, Position, BoardMove> {

    StateType getState(Position position);

    Board<StateType, Position, BoardMove> move(BoardMove move);

}
