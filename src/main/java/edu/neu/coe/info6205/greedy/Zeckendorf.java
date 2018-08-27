package edu.neu.coe.info6205.greedy;

import java.util.ArrayList;

public class Zeckendorf {

    public Zeckendorf() {
        fibonacci = new long[2];
        fibonacci[0] = 1;
        fibonacci[1] = 1;
    }

    /**
     * Get the Zeckendorf components of x
     *
     * @param x a positive number
     * @return a list of longs, each of which is a non-consecutive Fibonacci number, and which sum to x
     */
    public Iterable<Long> get(long x) {
        ArrayList<Long> result = new ArrayList<>();
        long remainder = x;
        while (remainder > 0) {
            long largest = getLargestFibonacci(remainder);
            result.add(largest);
            remainder = remainder - largest;
        }
        return result;
    }

    private long getLargestFibonacci(long x) {
        while (fibonacci[fibonacci.length - 1] < x) extendFibonacci();
        int index = fibonacci.length;
        while (fibonacci[index - 1] > x) index--;
        return fibonacci[index - 1];
    }

    private void extendFibonacci() {
        int length = fibonacci.length;
        long[] temp = new long[length * 2];
        System.arraycopy(fibonacci, 0, temp, 0, length);
        fibonacci = temp;
        for (int i = length; i < 2 * length; i++)
            fibonacci[i] = fibonacci[i - 2] + fibonacci[i - 1];
    }

    // for testing only
    private int size() {
        return fibonacci.length;
    }

    // for testing only
    private long fib(int x) {
        return fibonacci[x];
    }

    private long[] fibonacci;

}
