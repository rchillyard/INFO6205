package edu.neu.coe.info6205.Game.SinglePlayerGame;

import edu.neu.coe.info6205.Game.IGame;
import edu.neu.coe.info6205.Game.Move;
import edu.neu.coe.info6205.Game.Solver;
import edu.neu.coe.info6205.Game.Player;

import java.util.List;

import static edu.neu.coe.info6205.Game.SolverType.SingleTurnSolver;

public abstract class SinglePlayerGame<T> implements IGame, UserGameInterface<T> {

    private T[][] grid = null;
    private T[][] refGrid = null;
    private Player player = null;

    private long totalTimeTaken = 0;
    private int totalMovesTillNow = 0;

    private Boolean won = null;

    private SPGameCreator<T> gameCreator;

    private boolean singleTurnGameCompletion = false;

    SinglePlayerGame() {}

    protected SinglePlayerGame(SPGameCreator<T> gameCreator, int row, int column, boolean isBot,
                               Solver<T, SinglePlayerGame<T>> moveGenerator, boolean singleTurnGameCompletion) {
        this.gameCreator = gameCreator;
        this.gameCreator.initialize(row, column);
        this.grid = this.gameCreator.getPlayerGridView();
        this.refGrid = deepCopy(grid);
        this.singleTurnGameCompletion = singleTurnGameCompletion;
        this.player = new Player(1, isBot, moveGenerator);
    }


    public void run() {
        if (SingleTurnSolver.equals(player.getMoveGenerator().getType())) {
            move();
            isGameOver();
        } else {

        }
    }

    @Override
    public void move() {
        //need to implement
        T[][] deepCopy = deepCopy(grid);
        List<Move<T>> moves = null;
        player.getMoveGenerator().solve(this);
    }

    private T[][] deepCopy(T[][] grid) {
        return java.util.Arrays.stream(grid).map(el -> el.clone()).toArray($ -> grid.clone());
    }

    public int getTotalMovesTillNow() {
        return totalMovesTillNow;
    }

    public T[][] getGrid() {
        return grid;
    }
}
