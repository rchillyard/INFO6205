package edu.neu.coe.info6205.greedy;

/**
 * This performs memoization for Fibonacci numbers.
 */
public class Fibonacci {

    /**
     * Constructor which creates a Fibonacci array of length 2.
     */
    public Fibonacci() {
        fibonacci = new long[2];
        fibonacci[0] = 1;
        fibonacci[1] = 1;
    }

    /**
     * Get the largest Fibonacci number which is smaller than x.
     *
     * @param x the value x.
     * @return the largest Fibonacci number smaller than x.
     */
    public long getLargest(long x) {
        int index = fibonacci.length - 1;
        while (fibonacci[index] > x) index--;
        return fibonacci[index];
    }

    /**
     * Ensure that the array of Fibonacci numbers encompasses x
     *
     * @param x a number
     */
    public void ensure(long x) {
        while (fibonacci[fibonacci.length - 1] < x) extend();
    }

    // Extend the size of the Fibonacci array by doubling it.
    private void extend() {
        int length = fibonacci.length;
        long[] temp = new long[length * 2];
        System.arraycopy(fibonacci, 0, temp, 0, length);
        fibonacci = temp;
        for (int i = length; i < temp.length; i++)
            fibonacci[i] = calculate(i);
    }

    // This is the definition of the Fibonacci series.
    // NOTE: caller must ensure that 2 <= i <= fibonacci.length
    private long calculate(int i) {
        return 0; // TO BE IMPLEMENTED
    }

    // for testing only
    int size() {
        return fibonacci.length;
    }

    // for testing only
    long fib(int x) {
        return fibonacci[x];
    }

    private long[] fibonacci;
}
