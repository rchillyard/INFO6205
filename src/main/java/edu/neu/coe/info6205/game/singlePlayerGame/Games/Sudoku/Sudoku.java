package edu.neu.coe.info6205.game.singlePlayerGame.Games.Sudoku;

import edu.neu.coe.info6205.game.Move;
import edu.neu.coe.info6205.game.Player;
import edu.neu.coe.info6205.game.Solver;
import edu.neu.coe.info6205.game.generics.Board;
import edu.neu.coe.info6205.game.generics.GridPosition;
import edu.neu.coe.info6205.game.generics.SPGameCreator;
import edu.neu.coe.info6205.game.generics.StateTransition;
import edu.neu.coe.info6205.game.singlePlayerGame.SinglePlayerGame;
import edu.neu.coe.info6205.game.singlePlayerGame.UserGame;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Sudoku extends SinglePlayerGame<Integer, UserGame<Board<Integer, GridPosition, StateTransition<Integer, GridPosition>>, Integer>> {

    private final int minMoves;
    final HashSet<GridPosition> positionsToBeFilled;
    final HashSet<GridPosition> positionsAlreadyFilled;
    final List<Integer> oneToNine = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

    final int n = 9;

    private HashSet<GridPosition> getPositionsToFilled() {
        HashSet<GridPosition> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (getBoardGrid().getState(new GridPosition(i, j)) == null) {
                    set.add(new GridPosition(i, j));
                }
            }
        }
        return set;
    }

    private HashSet<GridPosition> getAlreadyFilledPositions() {
        HashSet<GridPosition> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (getBoardGrid().getState(new GridPosition(i, j)) != null) {
                    set.add(new GridPosition(i, j));
                }
            }
        }
        return set;
    }

    public Sudoku(SPGameCreator<Integer, GridPosition, StateTransition<Integer, GridPosition>> gameCreator, boolean isBot,
                  Solver<Integer, UserGame<Board<Integer, GridPosition, StateTransition<Integer, GridPosition>>, Integer>> moveGenerator, int size) {
        super(gameCreator, isBot, moveGenerator, size);
        this.positionsAlreadyFilled = getAlreadyFilledPositions();
        this.positionsToBeFilled = getPositionsToFilled();
        print(positionsToBeFilled);
        minMoves = positionsAlreadyFilled.size();
    }

    private void print(HashSet<GridPosition> setArray) {
        System.out.print("Position To be filled by Sudoku");
        for (GridPosition val : setArray) {
                System.out.print(val + ", ");
            }
            System.out.println();
    }
    /**
     *
     */
    @Override
    public void display() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0 || j == 3 || j == 6) System.out.print((j == 3 || j == 6 ? " " : "") + "|");
                System.out.print((getBoardGrid().getState(new GridPosition(i, j)) != null ? getBoardGrid().getState(new GridPosition(i, j)) : "_") + "|");
            }
            System.out.println();
            if (i == 2 || i == 5) {
                System.out.println("-----------------------");
            }
        }
        System.out.println("\n");
    }

    @Override
    public boolean isGameOver() {
        //display();
        return positionsToBeFilled.size() == 0;
    }

    @Override
    public Player<Integer, UserGame<Board<Integer, GridPosition, StateTransition<Integer, GridPosition>>, Integer>> getWinner() {
        if (!isGameOver()) {
            System.out.println("Game still not over");
            return null;
        }

        if (won == null) {
            checkWinner();
        }
        return won ? getPlayer() : null;
    }

    @Override
    public Player<Integer, UserGame<Board<Integer, GridPosition, StateTransition<Integer, GridPosition>>, Integer>> checkWinner() {
        if (!isGameOver()) return null;

        /*
        if (isSudokuHampered()) {
            System.out.println("Game was hampered methods other than fillWrapper were used to fill the grid");
            return null;
        }
         */

        for (int i = 0; i < n; i++) {
            if (!isColumnCorrect(i)) {
                System.out.println("Column incorrect of index i:" + i);
                won = false;
                return null;
            }

            if (!isRowCorrect(i)) {
                System.out.println("Row incorrect of index i: " + i);
                won = false;
                return null;
            }
        }

        for (int i = 0; i < n; i += 3) {
            for (int j = 0; j < n; j += 3) {
                if (!isGridConditionCorrect(i, i + 3, j, j + 3)) {
                    won = false;
                    return null;
                }
            }
        }
        won = true;
        return getPlayer();
    }

    @Override
    public boolean fill(StateTransition<Integer, GridPosition> move) {
        //Create a move Processor here
        GridPosition gridPosition = move.endPosition();
        Integer val = move.transitionFunction().apply(0);
        //Pair pair = new Pair(gridPosition.x, gridPosition.getColumn());
        if (positionsToBeFilled.contains(gridPosition)) {
            if (val != null) {
                positionsToBeFilled.remove(gridPosition);
                setBoard(getBoardGrid().move(move));
            }
            return true;
        } else if (positionsAlreadyFilled.contains(gridPosition)) {
            System.out.println("Cannot fill this position : " + gridPosition);
            return false;
        } else {
            if (val == null) {
                positionsToBeFilled.add(gridPosition);
            }
            setBoard(getBoardGrid().move(move));
            return true;
        }
    }

    @Override
    public StateTransition<Integer, GridPosition> createMoveProcessor(Move<Integer> move) {
        return new SudokuStateTransition(move.getRow(), move.getColumn(), move.getVal());
    }

    /**
     * To be implemented by the Solver for this. Is this needed?
     *
     * @param move the move.
     * @return true if valid.
     */
    @Override
    public boolean validateMove(Move<Integer> move) {
        return false;
    }


    public Board<Integer, GridPosition, StateTransition<Integer, GridPosition>> getBoardGrid() {
        return getBoard();
    }

    private boolean isRowCorrect(int row) {
        // XXX what's this?
        Set<Integer> set = oneToNine.stream().collect(Collectors.toSet());
        for (int i = 0; i < n; i++) {
            // TODO check this
            set.remove(getBoardGrid().getState(new GridPosition(row, i)));
        }
        return set.size() == 0;
    }

    private boolean isColumnCorrect(int column) {
        // XXX what's this?
        Set<Integer> set = oneToNine.stream().collect(Collectors.toSet());
        for (int i = 0; i < n; i++) {
            // TODO check this
            set.remove(getBoardGrid().getState(new GridPosition(i, column)));
        }
        return set.size() == 0;
    }

    public boolean isGridConditionCorrect(int rowStart, int rowEnd, int columnStart, int columnEnd) {
        // XXX what's this?
        Set<Integer> set = oneToNine.stream().collect(Collectors.toSet());
        for (int i = rowStart; i < rowEnd; i++) {
            for (int j = columnStart; j < columnEnd; j++) {
                // TODO check this
                set.remove(getBoardGrid().getState(new GridPosition(i, j)));
            }
        }
        return set.size() == 0;
    }

    private boolean isSudokuHampered() {
        /*Integer[][] grid = getGrid();
        Integer[][] refGrid = getRefGrid();
        List<Pair> hamperedPos = new LinkedList<>();
        for (Pair pair : this.positionsAlreadyFilled) {
            if (grid[pair.getX()][pair.getY()] != refGrid[pair.getX()][pair.getY()]) {
                hamperedPos.add(pair);
            }
        }
        System.out.println("hampered Pos : " + hamperedPos.size());
       return hamperedPos.size() != 0;
         */
        return false;
    }


}
