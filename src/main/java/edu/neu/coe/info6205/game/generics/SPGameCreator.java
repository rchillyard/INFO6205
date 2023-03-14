package edu.neu.coe.info6205.game.generics;

public abstract class SPGameCreator<Board> {
    public Board getBoard() {
        return board;
    }

    //public void setBoard(Board board) {
    //    this.board = board;
    //}

    public Board getPlayerView() {
        return playerView;
    }


    //public void setPlayerView(Board playerView) {
    //    this.playerView = playerView;
    //}

    private Board board;
    private Board playerView;

    public SPGameCreator(Board board, Board playerView) {
        this.board = board;
        this.playerView = playerView;
    }

    public SPGameCreator() {}

    public boolean initialize(Integer... args) {
        if (board == null) {
            board = createGame(args);
        }
        if (playerView == null) {
            playerView = createPlayerGameView(board);
        }
        return true;
    }

    protected abstract Board createGame(Integer... args);

    protected abstract Board createPlayerGameView(Board board);
}
