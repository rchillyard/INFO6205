package edu.neu.coe.info6205.Game.SinglePlayerGame;

import edu.neu.coe.info6205.Game.Game;
import edu.neu.coe.info6205.Game.Move;
import edu.neu.coe.info6205.Game.Solver;
import edu.neu.coe.info6205.Game.Player;

import static edu.neu.coe.info6205.Game.SolverType.SingleTurnSolver;

public abstract class SinglePlayerGame<T> implements Game, UserGame<T> {

    private T[][] grid = null;
    private T[][] refGrid = null;
    private Player player = null;

    private long ticks = 0;

    private boolean isClockRunning = false;

    protected Boolean won = null;

    private SPGameCreator<T> gameCreator;

    //SinglePlayerGame() {}

    protected SinglePlayerGame(SPGameCreator<T> gameCreator, int row, int column, boolean isBot,
                               Solver<T, UserGame<T>> moveGenerator) {
        this.gameCreator = gameCreator;
        this.gameCreator.initialize(row, column);
        this.grid = this.gameCreator.getPlayerGridView();
        this.refGrid = deepCopy(grid);
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
            T[][] deepCopy = deepCopy(grid);
            return player.getMoveGenerator().getMove(deepCopy);
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

    public T[][] getGrid() {
        return grid;
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
