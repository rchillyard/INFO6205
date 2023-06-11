package edu.neu.coe.info6205.sort.classic;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.GenericHelper;
import edu.neu.coe.info6205.util.Config;
import edu.neu.coe.info6205.util.Utilities;

import java.util.Random;
import java.util.function.Function;

public class ClassicHelper<X> implements GenericHelper<X> {
    public boolean instrumented() {
        return false;
    }

    public X[] random(int m, Class<X> clazz, Function<Random, X> f) {
        if (m <= 0)
            throw new BaseHelper.HelperException("Helper.random: requesting zero random elements (helper not initialized?)");
        randomArray = null;
        randomArray = Utilities.fillRandomArray(clazz, random, m, f);
        return randomArray;
    }

    @Override
    public void init(int n) {
        this.n = n;
    }

    public int getN() {
        return n;
    }

    public void close() {
    }

    @Override
    public String toString() {
        return "Helper for " + description + " with " + n + " elements";
    }

    public String getDescription() {
        return description;
    }

    public Config getConfig() {
        return config;
    }

    /**
     * Constructor for explicit random number generator.
     *
     * @param description the description of this Helper (for humans).
     * @param n           the number of elements expected to be sorted. The field n is mutable so can be set after the constructor.
     * @param random      a random number generator.
     */
    public ClassicHelper(String description, int n, Random random, Config config) {
        this.n = n;
        this.description = description;
        this.random = random;
        this.config = config;
    }


    private final String description;
    private final Random random;
    private final Config config;
    private int n;

    protected X[] randomArray;
}
