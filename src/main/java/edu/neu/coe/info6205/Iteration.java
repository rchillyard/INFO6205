/*
 * Copyright (c) 2018. Phasmid Software
 */

package edu.neu.coe.info6205;

public class Iteration {

    public static int sum(int[] array) {
        int result = 0;
        for (int i : array) result += i;
        return result;
    }

    public static void main(String[] args) {
        int[] array = new int[10];
        for (int i = 0; i < 10; i++) array[i] = i + 1;
        System.out.println(sum(array));
    }
}
