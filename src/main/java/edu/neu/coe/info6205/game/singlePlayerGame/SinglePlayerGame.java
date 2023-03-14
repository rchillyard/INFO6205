package edu.neu.coe.info6205.game.singlePlayerGame;

import edu.neu.coe.info6205.game.Game;
import edu.neu.coe.info6205.game.Move;
import edu.neu.coe.info6205.game.Solver;
import edu.neu.coe.info6205.game.Player;
import edu.neu.coe.info6205.game.generics.*;

import static edu.neu.coe.info6205.game.SolverType.SingleTurnSolver;

public abstract class SinglePlayerGame<T> implements Game, UserGame<Board, T> {

    private Board<T, GridPosition, Move<T>> board = null;
    private T[][] refGrid = null;
    private Player player = null;

    private long ticks = 0;

    private boolean isClockRunning = false;

    protected Boolean won = null;

    private SPGameCreator<Board<T, GridPosition, Move<T>>> gameCreator;

    //SinglePlayerGame() {}

    protected SinglePlayerGame(SPGameCreator<? extends Board> gameCreator, boolean isBot,
                               Solver<T, UserGame> moveGenerator, Integer... sizeArgs) {
        this.gameCreator = (SPGameCreator<Board<T, GridPosition, Move<T>>>) gameCreator;
        this.gameCreator.initialize(sizeArgs);
        this.board = this.gameCreator.getPlayerView();
        //this.refGrid = deepCopy(grid); //todo mehul need to solve
        this.player = new Player(1, isBot, moveGenerator);
    }


    public void run() {
        if (SingleTurnSolver.equals(player.getMoveGenerator().getType())) {
            startTime();
            move();
            isGameOver();
            checkWinner();
        } else {
            while (isGameOver()) {
                Move<T> move = null;
                do {
                    move = move();
                } while (fillWrapper(move));
            }
        }
    }

    @Override
    public Move<T> move() {
        if (SingleTurnSolver.equals(player.getMoveGenerator().getType())) {
            player.getMoveGenerator().solve(this);
            return null;
        } else {
            //T[][] deepCopy = deepCopy(grid);
            return null;//player.getMoveGenerator().getMove(grid.); todo mehul send grid or board and expect a move
        }
    }

    /**
     * For calculating time taken for one move.
     * @param move
     * @return
     */
    public boolean fillWrapper(Move<T> move) {
        long timeTaken = reset();
        boolean isValidMove = fill(move);
        player.addMove(move, timeTaken, isValidMove);
        startTime();
        return isValidMove;
    }

    /**
     * This method is meant only for Game creators to override
     * @param move
     * @return
     */
    public abstract boolean fill(Move<T> move);

    private T[][] deepCopy(T[][] grid) {
        return java.util.Arrays.stream(grid).map(el -> el.clone()).toArray($ -> grid.clone());
    }

    protected int getMovesPlayed() {
        return this.player.getValidNumberOfMoves();
    }

    public Board<T, GridPosition, Move<T>> getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    protected T[][] getRefGrid() {
        return refGrid;
    }

    protected Player getPlayer() {
        return player;
    }

    public void startTime() {
        isClockRunning = true;
        ticks -= getClock();
    }

    public void resume() {
        if (isClockRunning)
            throw new RuntimeException("Clock already running");
        ticks -= getClock();
    }

    public void pause() {
        if (!isClockRunning)
            throw new RuntimeException("Clock is not running");
        ticks += getClock();

    }

    public long reset() {
        long temp = ticks;
        temp += getClock();
        ticks = 0;
        isClockRunning = false;
        return temp;
    }

    private static long getClock() {
        return java.lang.System.nanoTime();
    }


}
