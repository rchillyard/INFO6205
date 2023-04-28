package edu.neu.coe.info6205.game.singlePlayerGame;

import edu.neu.coe.info6205.game.Game;
import edu.neu.coe.info6205.game.Move;
import edu.neu.coe.info6205.game.Player;
import edu.neu.coe.info6205.game.Solver;
import edu.neu.coe.info6205.game.generics.Board;
import edu.neu.coe.info6205.game.generics.GridPosition;
import edu.neu.coe.info6205.game.generics.SPGameCreator;
import edu.neu.coe.info6205.game.generics.StateTransition;

import static edu.neu.coe.info6205.game.SolverType.SingleTurnSolver;

public abstract class SinglePlayerGame<T, G> implements Game<T, G>,
        UserGame<Board<T, GridPosition, StateTransition<T, GridPosition>>, T> {

    private Board<T, GridPosition, StateTransition<T, GridPosition>> board;
    private final T[][] refGrid = null;
    private final Player<T, UserGame<Board<T, GridPosition, StateTransition<T, GridPosition>>, T>> player;

    private long ticks = 0;

    private boolean isClockRunning = false;

    protected Boolean won = null;

    //private final SPGameCreator<Board<T, GridPosition, StateTransition<T>>> gameCreator;

    //SinglePlayerGame() {}

    protected SinglePlayerGame(SPGameCreator<T, GridPosition, StateTransition<T, GridPosition>> gameCreator, boolean isBot,
                               Solver<T, UserGame<Board<T, GridPosition, StateTransition<T, GridPosition>>, T>> moveGenerator, Integer... sizeArgs) {
        gameCreator.initialize(sizeArgs);
        this.board = gameCreator.getPlayerView();
        //this.refGrid = deepCopy(grid); //todo mehul need to solve
        this.player = new Player<>(1, isBot, moveGenerator);
    }


    public void run() {
        if (SingleTurnSolver.equals(player.getMoveGenerator().getType())) {
            startTime();
            move();
            isGameOver();
            checkWinner();
        } else {
            while (isGameOver()) {
                Move<T> move;
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
     * @param move a move
     * @return true if valid move.
     */
    public boolean fillWrapper(Move<T> move) {
        long timeTaken = reset();
        StateTransition<T, GridPosition> stateTransition = createMoveProcessor(move);
        boolean isValidMove = fill(stateTransition);
        player.addMove(move, timeTaken, isValidMove);
        startTime();
        return isValidMove;
    }

    /**
     * This method is meant only for Game creators to override
     *
     * @param move the move.
     * @return true if valid.
     */
    public abstract boolean fill(StateTransition<T, GridPosition> move);

    public abstract StateTransition<T, GridPosition> createMoveProcessor(Move<T> move);

    /*
    private T[][] deepCopy(T[][] grid) {
        return java.util.Arrays.stream(grid).map(T[]::clone).toArray($ -> grid.clone());
    }
     */

    protected int getMovesPlayed() {
        return this.player.getValidNumberOfMoves();
    }

    public Board<T, GridPosition, StateTransition<T, GridPosition>> getBoard() {
        return board;
    }

    public void setBoard(Board<T, GridPosition, StateTransition<T, GridPosition>> board) {
        this.board = board;
    }

    protected T[][] getRefGrid() {
        return refGrid;
    }

    protected Player<T, UserGame<Board<T, GridPosition, StateTransition<T, GridPosition>>, T>> getPlayer() {
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
