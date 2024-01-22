/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.randomwalk;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class RandomWalk {

    private int x = 0;
    private int y = 0;

    private final Random random = new Random();

    /**
     * Private method to move the current position, that's to say the drunkard moves
     *
     * @param dx the distance he moves in the x direction
     * @param dy the distance he moves in the y direction
     */
    private void move(int dx, int dy) {
        try {
            System.out.println("Step taken in the x coordinate: " + dx + ", Step taken in the y coordinate: " + dy);
            // TO BE IMPLEMENTED  do move
            x = x + dx;
            y = y + dy;
            System.out.println("Reached: (" + x + ", " + y + ") after the last move");
        } catch (Exception e) {
            throw new RuntimeException("Not implemented");
        }
    }

    /**
     * Perform a random walk of m steps
     *
     * @param m the number of steps the drunkard takes
     */
    private void randomWalk(int m) {
        // TO BE IMPLEMENTED
        // For m steps, run the randomMove to make the man move around the plane
        try {
            for(int i=1; i<=m; i++) {
                System.out.println("step = " + i + " of " + m);
                randomMove();
            }
        } catch (Exception e) {
            throw new RuntimeException("implementation missing");
        }
    }

    /**
     * Private method to generate a random move according to the rules of the situation.
     * That's to say, moves can be (+-1, 0) or (0, +-1).
     */
    private void randomMove() {
        boolean ns = random.nextBoolean();
        int step = random.nextBoolean() ? 1 : -1;
        move(ns ? step : 0, ns ? 0 : step);
    }

    /**
     * Method to compute the distance from the origin (the lamp-post where the drunkard starts) to his current position.
     *
     * @return the (Euclidean) distance from the origin to the current position.
     */

    // This function calculates the distance traveled by the man using the distance formula
    public double distance() {
        // TO BE IMPLEMENTED 
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    /**
     * Perform multiple random walk experiments, returning the mean distance.
     *
     * @param m the number of steps for each experiment
     * @param n the number of experiments to run
     * @return the mean distance
     */
    public static double randomWalkMulti(int m, int n) {
        double totalDistance = 0;
        for (int i = 1; i <= n; i++) {
            RandomWalk walk = new RandomWalk();
            // Track n
            System.out.println("N = " + i);
            // set new x and y after moving m steps
            walk.randomWalk(m);
            // keep adding the total distance at each level
            totalDistance = totalDistance + walk.distance();
            // show the distance walked in this particular iteration
            System.out.println("distance walked in this iteration = " + walk.distance());
            System.out.println("****************************");
        }
        // return the mean of the total distance the man walked over N iteration walking m steps each time
        return totalDistance / n;
    }

    public static void main(String[] args) {
        if (args.length == 0)
            throw new RuntimeException("Syntax: RandomWalk steps [experiments]");
        int m = Integer.parseInt(args[0]);  // initial value of m
        int n = 10;
        if (args.length > 1) n = Integer.parseInt(args[1]);
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter the number of movements/experiments in a single iteration: (Enter the value of N) ");
//        int n = scanner.nextInt();
        // Allow users to enter 6 different m values
        for(int i=0; i<6; i++) {
            System.out.println("m = " + m);
//            System.out.println("Enter the number of steps taken by the man: ");
//            int m = scanner.nextInt();
            double meanDistance = randomWalkMulti(m, n);
            System.out.println(m + " steps, mean Distance: " + meanDistance + " over " + n + " experiments\n");
            // increment m to make a new m value - auto updating of m to form new test cases
            m += 1;
        }
//        scanner.close();
    }
}