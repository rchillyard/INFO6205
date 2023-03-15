package edu.neu.coe.info6205.game;

import java.util.ArrayList;
import java.util.List;

public class PlayerStats<T> {

    private int numberOfMoves = 0;
    private int numberOfValidMoves = 0;
    private final List<MoveStats<T>> moveStatsList;

    PlayerStats() {
        moveStatsList = new ArrayList<>();
    }

    public void add(MoveStats<T> move) {
        moveStatsList.add(move);
        numberOfMoves++;
        if (move.validMove) numberOfValidMoves++;
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public int getNumberOfValidMoves() { return numberOfValidMoves; }

    public List<MoveStats<T>> getMoveStatsList() {
        return moveStatsList;
    }

    public long getTotalTime() {
        return moveStatsList.stream().map(p -> p.timeInMillis).reduce(0L, Long::sum);
    }

    public double getAverageTimePerMove() {
        return (double) getTotalTime()/moveStatsList.size();
    }
}
