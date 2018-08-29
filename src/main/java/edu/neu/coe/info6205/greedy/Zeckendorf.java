package edu.neu.coe.info6205.greedy;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class models Zeckendorf's Theorem: https://en.wikipedia.org/wiki/Zeckendorf%27s_theorem
 * It is an example of a greedy algorithm.
 * This class does all its work directly (without using the Greedy class);
 * for an example of Zeckendorf which does use Greedy, please see GreedyTest.java
 */
public class Zeckendorf {

    public Zeckendorf() {
        fibonacci = new long[2];
        fibonacci[0] = 1;
        fibonacci[1] = 1;
    }

    /**
     * Get the Zeckendorf representation of x
     *
     * @param x a positive number
     * @return a list of longs, each of which is a non-consecutive Fibonacci number, and which sum to x
     */
    public Iterable<Long> get(long x) {
        ensureFibonacci(x);
        return getZeckendorfRepresentation(x);
    }

    // This method gets the Zeckendorf representation for x
    private Iterable<Long> getZeckendorfRepresentation(long x) {
        Collection<Long> result = new ArrayList<>();
        long remainder = x;
        while (remainder > 0) {
            long greedy = getLargestFibonacci(remainder);
            result.add(greedy);
            remainder = remainder - greedy;
        }
        return result;
    }

    // This is the definition of the Fibonacci series.
    // NOTE: caller must ensure that 2 <= i <= fibonacci.length
    private long calculateFibonacci(int i) {
        return fibonacci[i - 2] + fibonacci[i - 1];
    }

    // Get the largest Fibonacci number which is smaller than x
    private long getLargestFibonacci(long x) {
        int index = fibonacci.length - 1;
        while (fibonacci[index] > x) index--;
        return fibonacci[index];
    }

    // Ensure the set of Fibonacci numbers is long enough
    private void ensureFibonacci(long x) {
        while (fibonacci[fibonacci.length - 1] < x) extendFibonacci();
    }

    private void extendFibonacci() {
        int length = fibonacci.length;
        long[] temp = new long[length * 2];
        System.arraycopy(fibonacci, 0, temp, 0, length);
        fibonacci = temp;
        for (int i = length; i < temp.length; i++)
            fibonacci[i] = calculateFibonacci(i);
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
