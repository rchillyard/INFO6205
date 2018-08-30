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
        fibonacci = new Fibonacci();
    }

    /**
     * Get the Zeckendorf representation of x
     *
     * @param x a positive number
     * @return a list of longs, each of which is a non-consecutive Fibonacci number, and which sum to x
     */
    public Iterable<Long> get(long x) {
        fibonacci.ensure(x);
        return getZeckendorfRepresentation(x);
    }

    // This method gets the Zeckendorf representation for x
    private Iterable<Long> getZeckendorfRepresentation(long x) {
        Collection<Long> result = new ArrayList<>();
        long remainder = x;
        while (remainder > 0) {
            long greedy = fibonacci.getLargest(remainder);
            result.add(greedy);
            remainder = remainder - greedy;
        }
        return result;
    }

    private final Fibonacci fibonacci;

}
