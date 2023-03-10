package edu.neu.coe.info6205.Game;

public class Player<T, G> {
    private int playerId;
    private boolean isBot;

    private PlayerStats<T> playerStats;
    private Solver<T, G> moveGenerator;

    public Player(int playerId, boolean isBot) {
        this.playerId = playerId;
        this.isBot = isBot;
        playerStats = new PlayerStats<T>();
    }

    public Player(int playerId, boolean isBot, Solver<T, G> moveGenerator) {
        this.playerId = playerId;
        this.isBot = isBot;
        this.moveGenerator = moveGenerator;
        playerStats = new PlayerStats<T>();
    }

    public void addMove(Move<T> move, long timeInMilli, boolean validMove) {
        this.playerStats.add(new MoveStats<>(move, timeInMilli, validMove));
    }

    public int getPlayerId() {
        return playerId;
    }

    public boolean isBot() {
        return isBot;
    }

    public int getNumberOfMoves() {
        return playerStats.getNumberOfMoves();
    }

    public int getValidNumberOfMoves() {
        return playerStats.getNumberOfValidMoves();
    }

    public Solver<T, G> getMoveGenerator() {
        return moveGenerator;
    }

    public long getTotalTime() {
        return playerStats.getTotalTime();
    }

    public double getAverageTimePerMove() {
        return playerStats.getAverageTimePerMove();
    }




}
