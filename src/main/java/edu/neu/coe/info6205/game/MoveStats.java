package edu.neu.coe.info6205.game;

public class MoveStats<T> {

    final Move<T> move;
    final Long timeInMillis;

    final Boolean validMove;

    MoveStats(Move<T> move, long timeInMillis, boolean validMove) {
        this.move = move;
        this.timeInMillis = timeInMillis;
        this.validMove = validMove;
    }
}
