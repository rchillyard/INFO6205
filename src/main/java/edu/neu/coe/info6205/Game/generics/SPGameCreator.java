package edu.neu.coe.info6205.Game.generics;

public abstract class SPGameCreator<Board> {
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getPlayerView() {
        return playerView;
    }

    public void setPlayerView(Board playerView) {
        this.playerView = playerView;
    }

    private Board board;
    private Board playerView;

    public SPGameCreator(Board board, Board playerView) {
        this.board = board;
        this.playerView = playerView;
    }

    public boolean initialize(int row, int column) {
        if (board == null) {
            board = createGame(row, column);
        }
        if (playerView == null) {
            playerView = createPlayerGameView(board);
        }
        return true;
    }

    protected abstract Board createGame(int row, int column);

    protected abstract Board createPlayerGameView(Board board);
}
