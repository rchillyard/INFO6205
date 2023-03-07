package edu.neu.coe.info6205.Game.SinglePlayerGame;

public abstract class SPGameCreator<T> {
    private T[][] grid;
    private T[][] playerGridView;


    public boolean initialize(int row, int column) {
        if (grid == null) {
            grid = createGame(row, column);
        }
        if (playerGridView == null) {
            playerGridView = createPlayerGameView();
        }
        return true;
    }

    protected abstract T[][] createGame(int row, int column);

    protected abstract T[][] createPlayerGameView();

    public T[][] getGrid() {
        return grid;
    }

    public void setGrid(T[][] grid) {
        this.grid = grid;
    }

    public T[][] getPlayerGridView() {
        return playerGridView;
    }

    public void setPlayerGridView(T[][] playerGridView) {
        this.playerGridView = playerGridView;
    }
}
