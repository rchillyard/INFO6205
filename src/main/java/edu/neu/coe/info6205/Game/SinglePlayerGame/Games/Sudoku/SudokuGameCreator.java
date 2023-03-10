package edu.neu.coe.info6205.Game.SinglePlayerGame.Games.Sudoku;

import edu.neu.coe.info6205.Game.SinglePlayerGame.SPGameCreator;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class SudokuGameCreator extends SPGameCreator<Integer> {

    List<Integer> oneToNine = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

    @Override
    protected Integer[][] createGame(int row, int column) {

        Integer[][] smallerGrid = getRandomGrid(row);
        Integer[][] largerGrid = new Integer[row*row][row*row];
        for (int j = 0; j < row*row; j += row) {
            for (int i = 0; i < row*row; i += row) {
                System.out.println("i: " + i + " j: " + j);
                fillLargerBySmaller(smallerGrid, largerGrid, i, j);
                shiftByValue(1, row, smallerGrid);
            }
        }
        //Integer[][]

        //shiftByValue(1, row, smallerGrid);
        display(largerGrid);

        return new Integer[][] {
                {4, 3, 5, 2, 6, 9, 7, 8, 1},
                {6, 8, 2, 5, 7, 1, 4, 9, 3},
                {1, 9, 7, 8, 3, 4, 5, 6, 2},
                {8, 2, 6, 1, 9, 5, 3, 4, 7},
                {3, 7, 4, 6, 8, 2, 9, 1, 5},
                {9, 5, 1, 7, 4, 3, 6, 2, 8},
                {5, 1, 9, 3, 2, 6, 8, 7, 4},
                {2, 4, 8, 9, 5, 7, 1, 3, 6},
                {7, 6, 3, 4, 1, 8, 2, 5, 9}
        };

          /*

        return new Integer[][] {
                {1, 2, 6, 4, 3, 7, 9, 5, 8},
                {8, 9, 5, 6, 2, 1, 4, 7, 3},
                {3, 7, 4, 9, 8, 5, 1, 2, 6},
                {4, 5, 7, 1, 9, 3, 8, 6, 2},
                {9, 8, 3, 2, 4, 6, 5, 1, 7},
                {6, 1, 2, 5, 7, 8, 3, 9, 4},
                {2, 6, 9, 3, 1, 4, 7, 8, 5},
                {5, 4, 8, 7, 6, 9, 2, 3, 1},
                {7, 3, 1, 8, 5, 2, 6, 4, 9}
        };

           */
    }


    private void fillLargerBySmaller(Integer[][] smaller, Integer[][] larger, int rowStart, int columnStart) {
        for (int i = rowStart, si = 0; i < rowStart + smaller.length; i++, si++) {
            for (int j = columnStart, sj = 0; j < columnStart + smaller.length; j++, sj++) {
                larger[i][j] = smaller[si][sj];
            }
        }
    }

    private void print(Integer[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j]+",");
            }
            System.out.println();
        }
    }

    private Integer[][] getRandomGrid(int n) {
        Integer[][] smallerGrid = new Integer[n][n];
        int max = n*n;
        HashSet set = getSetTillN(max);
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                while (true) {
                    int val = random.nextInt(max + 1);
                    if (set.contains(val)) {
                        smallerGrid[i][j] = val;
                        set.remove(val);
                        break;
                    }
                }
            }
        }
        return smallerGrid;
    }

    private void shiftByValue(int shift, int n, Integer[][] grid) {
        int[] ints = new int[n*n];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ints[index++] = grid[i][j];
            }
        }

        index = shift % (n*n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = ints[index++];
                if (index == ints.length)
                    index = 0;
            }
        }

    }

    private HashSet<Integer> getSetTillN(int n) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i <= n; i++) {set.add(i);}
        return set;
    }

    @Override
    protected Integer[][] createPlayerGameView(Integer[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {

            }
        }

        return new Integer[][] {
                {null, null, null, 2, 6, null, 7, null, 1},
                {6, 8, null, null, 7, null, null, 9, null},
                {1, 9, null, null, null, 4, 5, null, null},
                {8, 2, null, 1, null, null, null, 4, null},
                {null, null, 4, 6, null, 2, 9, null, null},
                {null, 5, null, null, null, 3, null, 2, 8},
                {null, null, 9, 3, null, null, null, 7, 4},
                {null, 4, null, null, 5, null, null, 3, 6},
                {7, null, 3, null, 1, 8, null, null, null}
        };

/*
        return new Integer[][] {
                {null, 2, null, null, null, null, null, null, null},
                {null, null, null, 6, null, null, null, null, 3},
                {null, 7, 4, null, 8, null, null, null, null},
                {null, null, null, null, null, 3, null, null, 2},
                {null, 8, null, null, 4, null, null, 1, null},
                {6, null, null, 5, null, null, null, null, null},
                {null, null, null, null, 1, null, 7, 8, null},
                {5, null, null, null, null, 9, null, null, null},
                {null, null, null, null, null, null, null, 4, null}
        };

 */
    }


    public void display(Integer[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j == 0 || j == 3 || j == 6) System.out.print((j == 3 || j == 6 ? " " : "") + "|");
                System.out.print((grid[i][j] != null ? grid[i][j] : "_") + "|");
            }
            System.out.println();
            if (i == 2 || i == 5) {
                System.out.println("-----------------------");
            }
        }
        System.out.println("\n");
    }

    private boolean isRowCorrect(int row) {
        Set<Integer> set = oneToNine.stream().collect(Collectors.toSet());
        for (int i = 0; i < 9; i++) {
            set.remove(getGrid()[row][i]);
        }
        return set.size() == 0;
    }

    private boolean isColumnCorrect(int column) {
        Set<Integer> set = oneToNine.stream().collect(Collectors.toSet());
        for (int i = 0; i < 9; i++) {
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
}