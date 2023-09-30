/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205;

class NewtonApproximation {
    public static void main(String[] args) {
        // Newton's Approximation to solve cos(x) = x
        double x = 1.0;
        int left = 200;
        for (; left > 0; left--) {
            final double y = Math.cos(x) - x;
            if (Math.abs(y) < 1E-7) {
                System.out.println("the solution to cos(x)=x is: " + x);
                System.exit(0);
            }
            x = x + y / (Math.sin(x) + 1);
        }
    }
}