/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205;

import java.util.Arrays;

public class Matrix {

    private final int rows;
    private final int columns;
    private final double[][] values;

    public Matrix(final int rows, final int columns, final double[][] values) {
        super();
        this.rows = rows;
        this.columns = columns;
        this.values = values;
    }

    public Matrix multiply(final Matrix other) {
        if (this.columns == other.rows) {
            double[][] result = new double[rows][other.columns];
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < other.columns; j++) {
                    double x = 0;
                    for (int k = 0; k < this.columns; k++) x += this.values[i][k] * other.values[k][j];
                    result[i][j] = x;
                }
            }
            return new Matrix(this.rows, other.columns, result);
        } else
            throw new RuntimeException("incompatible matrices");
    }

    public static void main(String[] args) {
        Arrays.sort(args);
        double[][] aVals = new double[2][2];
        aVals[0][0] = 1;
        aVals[0][1] = 2;
        aVals[1][0] = 3;
        aVals[1][1] = 4;
        double[][] bVals = new double[2][2];
        bVals[0][0] = -1;
        bVals[0][1] = 2;
        bVals[1][0] = -1;
        bVals[1][1] = 0;

        Matrix a = new Matrix(2, 2, aVals);
        Matrix b = new Matrix(2, 2, bVals);
        Matrix c = a.multiply(b);
        System.out.println(c.rows);
        System.out.println(c.columns);
        for (int i = 0; i < c.rows; i++) {
            for (int j = 0; j < c.columns; j++) {
                System.out.println(c.values[i][j]);
            }
        }
    }

}