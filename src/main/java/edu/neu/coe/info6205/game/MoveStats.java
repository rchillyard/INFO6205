package edu.neu.coe.info6205.game;

public class MoveStats<T> {

    Move<T> move;
    Long timeInMillis;

    Boolean validMove;

    MoveStats(Move<T> move, long timeInMillis, boolean validMove) {
        this.move = move;
        this.timeInMillis = timeInMillis;
        this.validMove = validMove;
    }
}
