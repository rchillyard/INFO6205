package edu.neu.coe.info6205.Game.SinglePlayerGame.Games.Sudoku;

import edu.neu.coe.info6205.Game.Move;
import edu.neu.coe.info6205.Game.SinglePlayerGame.UserGame;
import edu.neu.coe.info6205.Game.Solver;
import edu.neu.coe.info6205.Game.SolverType;
import edu.neu.coe.info6205.util.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SudokuSolver implements Solver<Integer, UserGame<Integer>> {

    private static final SolverType type = SolverType.SingleTurnSolver;

    List<Integer> oneToNine = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
    List<Integer> oneToFour = List.of(1, 2, 3, 4);

    HashSet<Integer>[] rowArray;
    HashSet<Integer>[] columnArray;
    HashSet<Integer>[] gridArray;

    int n = 9;

    HashSet<Integer> hashSet;

    @Override
    public void solve(UserGame<Integer> game) {

        int n = 9;
        HashSet<Integer>[] rowArray = new HashSet[n];
        HashSet<Integer>[] columnArray = new HashSet[n];
        HashSet<Integer>[] gridArray = new HashSet[n];
        hashSet = new HashSet<>();

        HashSet<Pair> positionToBeFilled = new HashSet<>();
        fillSets(game.getGrid(), positionToBeFilled, rowArray, columnArray, gridArray);
        System.out.println();
        Pair pair = (Pair) positionToBeFilled.toArray()[0];
        positionToBeFilled.remove(pair);
        solveRecursive(pair.getX(), pair.getY(), game, positionToBeFilled, rowArray, columnArray, gridArray);
    }

    @Override
    public Move<Integer> getMove(Integer[][] grid) {
        return null;
    }

    @Override
    public SolverType getType() {
        return type;
    }


    public boolean solveRecursive(int i, int j, UserGame<Integer> game, HashSet<Pair> positionToBeFilled, HashSet<Integer>[] rowArray,
                                  HashSet<Integer>[] columnArray, HashSet<Integer>[] gridArray) {
        //((SudokuGame)game).displayGame();
        //System.out.println( "i: " + i + " j: " + j);
        Integer hashGrid = getHash(game.getGrid());
        if (hashSet.contains(hashGrid)) {
            return false;
        }
        Set<Integer> possibleValues = getValuesForPositions(i, j, rowArray, columnArray, gridArray);
        //if (i == 0 && j == 0)
            //System.out.println("valuesWhich can be filled :" + possibleValues
            //.size() + " for i: " + i + " j: " + j);
        if (possibleValues.size() == 0) {
            //System.out.println("In here for Position i: " + i + " j: " + j);
            //((SudokuGame) game).display();
            return false;
        }

        for (Integer value : possibleValues) {

            fillValues(i, j, value, game, positionToBeFilled, rowArray, columnArray, gridArray);

            if (game.isGameOver()) {
                System.out.println("Game is over");
                return true;
            }
            //boolean isCorrectBranch = false;
            List<Pair> pairs = new ArrayList<>(positionToBeFilled);
            System.out.println("pair size " + pairs.size());
            for (int x = 0; x < pairs.size(); x++) {
                Pair pair = pairs.get(x);
                if (solveRecursive(pair.getX(), pair.getY(), game, positionToBeFilled, rowArray, columnArray, gridArray)) {
                    return true;
                }
            }

            removeValues(i, j, value, game, positionToBeFilled, rowArray, columnArray, gridArray);
        }
        //System.out.println("Finally Here: size left"+ positionToBeFilled.size());
        //((SudokuGame)game).displayGame();
        hashSet.add(hashGrid);
        return false;

    }

    public void fillValues(int i, int j, Integer val, UserGame<Integer> game, HashSet<Pair> positionToBeFilled, HashSet<Integer>[] rowArray,
                              HashSet<Integer>[] columnArray, HashSet<Integer>[] gridArray) {
        rowArray[i].add(val);
        columnArray[j].add(val);
        gridArray[getGrid(i, j)].add(val);
        positionToBeFilled.remove(new Pair(i, j));
        game.fillWrapper(new Move<>(i, j, val));
    }

    public void removeValues(int i, int j, Integer val, UserGame<Integer> game, HashSet<Pair> positionToBeFilled,
                             HashSet<Integer>[] rowArray, HashSet<Integer>[] columnArray, HashSet<Integer>[] gridArray) {
        rowArray[i].remove(val);
        columnArray[j].remove(val);
        gridArray[getGrid(i, j)].remove(val);
        positionToBeFilled.add(new Pair(i, j));
        game.fillWrapper(new Move<>(i, j, null));
    }

    public Set<Integer> getValuesForPositions(int i, int j,
                                              HashSet<Integer>[] rowArray, HashSet<Integer>[] columnArray,
                                              HashSet<Integer>[] gridArray) {
        Set<Integer> set = oneToNine.stream().collect(Collectors.toSet());

        set.removeAll(rowArray[i]);
        set.removeAll(columnArray[j]);
        set.removeAll(gridArray[getGrid(i, j)]);

        return set;
    }


    private int getGrid(int row, int column) {
        return 3 * (row / 3) + column / 3;
        //return 2 * (row / 2) + column / 2;
    }

    private int getHash(Integer[][] grid) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(grid[i][j] != null ? grid[i][j] : "_");
            }
        }
        return sb.toString().hashCode();
    }
    private void fillSets(Integer[][] grid, HashSet<Pair> positionToBeFilled, HashSet<Integer>[] rowArray,
                          HashSet<Integer>[] columnArray, HashSet<Integer>[] gridArray) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (rowArray[i] == null)
                    rowArray[i] = new HashSet<>();


                if (columnArray[j] == null)
                    columnArray[j] = new HashSet<>();


                int gridIndex = getGrid(i, j);
                if (gridArray[gridIndex] == null)
                    gridArray[gridIndex] = new HashSet<>();

                if (grid[i][j] != null) {
                    gridArray[gridIndex].add(grid[i][j]);
                    rowArray[i].add(grid[i][j]);
                    columnArray[j].add(grid[i][j]);
                } else {
                    System.out.println("here");
                    positionToBeFilled.add(new Pair(i, j));
                }
            }
        }

       /* for (int i = 0; i < 9; i++) {
            System.out.println()
            System.out.println(rowArray[i]);
            System.out.println(columnArray[i]);
            System.out.println(gridArray[i]);
        }
        */
    }


}
