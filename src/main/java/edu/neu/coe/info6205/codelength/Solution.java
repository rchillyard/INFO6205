/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.codelength;

/**
 * @author prospace
 */
public class Solution {
    private final double[][][] f = new double[2][5100][53];

    public double work1(int n, int length) {
        f[0][0][0] = 1.0;
        double ans = 0.0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < Math.min(n, 100); j++)
                f[1][i][j] = f[0][i - 1][j - 1] * 0.25;
            for (int j = 0; j < Math.min(n, 100); j++) {
                if (j == length) ans += f[0][i - 1][j] * 0.75;
                else f[1][i][0] += f[0][i - 1][j] * 0.75;
            }
            for (int j = 0; j < Math.min(n, 100); j++) {
                f[0][i][j] = f[1][i][j];
                f[1][i][j] = 0;
            }
        }

        ans += f[0][n][length];

        return ans;
    }
}
