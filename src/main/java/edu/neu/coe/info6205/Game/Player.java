package edu.neu.coe.info6205.Game;

public class Player<T, G> {
    private int playerId;
    private boolean isBot;
    private int numberOfMoves;
    private Solver<T, G> moveGenerator;

    public Player(int playerId, boolean isBot) {
        this.playerId = playerId;
        this.isBot = isBot;
    }

    public Player(int playerId, boolean isBot, Solver<T, G> moveGenerator) {
        this.playerId = playerId;
        this.isBot = isBot;
        this.moveGenerator = moveGenerator;
    }

    public int incrementMove() {
        return ++this.numberOfMoves;
    }

    public int getPlayerId() {
        return playerId;
    }

    public boolean isBot() {
        return isBot;
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public Solver<T, G> getMoveGenerator() {
        return moveGenerator;
    }


}
