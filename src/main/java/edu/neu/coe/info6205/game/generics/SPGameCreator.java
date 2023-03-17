package edu.neu.coe.info6205.game.generics;

public abstract class SPGameCreator<StateType, Position, BoardMove> {
    public Board<StateType, Position, BoardMove> getBoard() {
        return board;
    }

    //public void setBoard(Board board) {
    //    this.board = board;
    //}

    public Board<StateType, Position, BoardMove> getPlayerView() {
        return playerView;
    }


    //public void setPlayerView(Board playerView) {
    //    this.playerView = playerView;
    //}

    private Board<StateType, Position, BoardMove> board;
    private Board<StateType, Position, BoardMove> playerView;

    // TODO re-invent this

//    public SPGameCreator(Board<StateType, Position, BoardMove> board, Board<StateType, Position, BoardMove> playerView) {
//        this.board = board;
//        this.playerView = playerView;
//    }

    public SPGameCreator() {}

    // TODO get rid of this
    public boolean initialize(Integer... args) {
        if (board == null) {
            board = createGame(args);
        }
        if (playerView == null) {
            playerView = createPlayerGameView(board);
        }
        return true;
    }

    protected abstract Board<StateType, Position, BoardMove> createGame(Integer... args);

    protected abstract Board<StateType, Position, BoardMove> createPlayerGameView(Board<StateType, Position, BoardMove> board);
}
