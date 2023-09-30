package edu.neu.coe.info6205;

import java.util.Random;

public class Counter {

    /**
     * Constructor to construct a new Counter with count initialized to zero.
     *
     * @param id the identifier by which we can distinguish this Counter.
     */
    public Counter(String id) {
        this.id = id;
    }

    /**
     * Mutating method to increase the count.
     */
    public void increment() {
        count++;
    }

    /**
     * Non-mutating method to return the current count.
     *
     * @return the value of count.
     */
    public int tally() {
        return count;
    }

    @Override
    public String toString() {
        return id + ": " + count;
    }

    public static void main(String[] args) {
        Counter heads = new Counter("heads");
        Counter tails = new Counter("tails");
        Random random = new Random();
        for (int i = 0; i < 100; i++)
            if (random.nextBoolean()) heads.increment();
            else tails.increment();
        System.out.println(heads);
        System.out.println(tails);
    }

    private final String id;
    private int count = 0;
}