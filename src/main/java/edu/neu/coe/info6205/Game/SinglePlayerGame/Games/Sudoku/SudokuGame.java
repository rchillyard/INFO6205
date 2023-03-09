package edu.neu.coe.info6205.Game.SinglePlayerGame.Games.Sudoku;

import edu.neu.coe.info6205.Game.Move;
import edu.neu.coe.info6205.Game.SinglePlayerGame.UserGame;
import edu.neu.coe.info6205.Game.Solver;
import edu.neu.coe.info6205.Game.Player;
import edu.neu.coe.info6205.Game.SinglePlayerGame.SPGameCreator;
import edu.neu.coe.info6205.Game.SinglePlayerGame.SinglePlayerGame;
import edu.neu.coe.info6205.util.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SudokuGame extends SinglePlayerGame<Integer> {

    private int minMoves = 0;
    HashSet<Pair> positionsToBeFilled;
    HashSet<Pair> positionsAlreadyFilled;

    List<Integer> oneToNine = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

    int n = 9;

    private HashSet<Pair> getPositionsToFilled() {
        HashSet<Pair> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (getGrid()[i][j] == null) {
                    set.add(new Pair(i, j));
                }
            }
        }
        return set;
    }

    private HashSet<Pair> getAlreadyFilledPositions() {
        HashSet<Pair> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (getGrid()[i][j] != null) {
                    set.add(new Pair(i, j));
                }
            }
        }
        return set;
    }

    public SudokuGame(SPGameCreator<Integer> gameCreator, boolean isBot,
                      Solver<Integer, UserGame<Integer>> moveGenerator) {
        super(gameCreator, 9, 9, isBot, moveGenerator);
        this.positionsAlreadyFilled = getAlreadyFilledPositions();
        this.positionsToBeFilled = getPositionsToFilled();
        minMoves = positionsAlreadyFilled.size();
    }
    /**
     *
     */
    @Override
    public void display() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0 || j == 3 || j == 6) System.out.print((j == 3 || j == 6 ? " " : "") + "|");
                System.out.print((getGrid()[i][j] != null ? getGrid()[i][j] : "_") + "|");
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
        return positionsToBeFilled.size() == 0;
    }

    @Override
    public Player getWinner() {
        if (!isGameOver()) {
            System.out.println("Game still not over");
            return null;
        }

        if (won == null) {
            checkWinner();
        }
        return won ? getWinner() : null;
    }


    @Override
    public Player checkWinner() {
        if (!isGameOver()) return null;

        if (isSudokuHampered()) {
            System.out.println("Game was hampered methods other than fillWrapper were used to fill the grid");
            return null;
        }


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

        for (int i = 0; i < n; i += 2) {
            for (int j = 0; j < n; j += 2) {
                if (!isGridConditionCorrect(i, i + 2, j, j + 2)) {
                    won = false;
                    return null;
                }
            }
        }
        won = true;
        return getPlayer();
    }

    @Override
    public boolean fill(Move<Integer> move) {
        Pair pair = new Pair(move.getRow(), move.getColumn());
        if (positionsToBeFilled.contains(pair)) {
            if (move.getVal() != null) {
                positionsToBeFilled.remove(pair);
                getGrid()[pair.getX()][pair.getY()] = move.getVal();
            }
            return true;
        } else if (positionsAlreadyFilled.contains(pair)) {
            System.out.println("Cannot fill this position : " + pair);
            return false;
        } else {
            if (move.getVal() == null) {
                positionsToBeFilled.add(pair);
            }
            getGrid()[pair.getX()][pair.getY()] = move.getVal();
            return true;
        }
    }

    /**
     * To be implemented by the Solver for this. Is this needed?
     * @param move
     * @return
     */
    @Override
    public boolean validateMove(Move move) {
        return false;
    }

    private boolean isRowCorrect(int row) {
        Set<Integer> set = oneToNine.stream().collect(Collectors.toSet());
        for (int i = 0; i < n; i++) {
            set.remove(getGrid()[row][i]);
        }
        return set.size() == 0;
    }

    private boolean isColumnCorrect(int column) {
        Set<Integer> set = oneToNine.stream().collect(Collectors.toSet());
        for (int i = 0; i < n; i++) {
            set.remove(getGrid()[i][column]);
        }
        return set.size() == 0;
    }

    public boolean isGridConditionCorrect(int rowStart, int rowEnd, int columnStart, int columnEnd) {
        Set<Integer> set = oneToNine.stream().collect(Collectors.toSet());
        for (int i = rowStart; i < rowEnd; i++) {
            for (int j = columnStart; j < columnEnd; j++) {
                set.remove(getGrid()[i][j]);
            }
        }
        return set.size() == 0;
    }

    private boolean isSudokuHampered() {
        Integer[][] grid = getGrid();
        Integer[][] refGrid = getRefGrid();
        List<Pair> hamperedPos = new LinkedList<>();
        for (Pair pair : this.positionsAlreadyFilled) {
            if (grid[pair.getX()][pair.getY()] != refGrid[pair.getX()][pair.getY()]) {
                hamperedPos.add(pair);
            }
        }
        System.out.println("hampered Pos : " + hamperedPos.size());
        return hamperedPos.size() != 0;
    }


}
