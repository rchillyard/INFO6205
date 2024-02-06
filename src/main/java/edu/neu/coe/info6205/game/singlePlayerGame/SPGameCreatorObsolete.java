package edu.neu.coe.info6205.game.singlePlayerGame;

public abstract class SPGameCreatorObsolete<T> {
    private T[][] grid;
    private T[][] playerGridView;


    public boolean initialize(int row, int column) {
        if (grid == null) {
            grid = createGame(row, column);
        }
        if (playerGridView == null) {
            playerGridView = createPlayerGameView(grid);
        }
        return true;
    }

    protected abstract T[][] createGame(int row, int column);

    protected abstract T[][] createPlayerGameView(T[][] grid);

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
