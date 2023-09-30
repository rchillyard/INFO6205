package edu.neu.coe.info6205.util;

/**
 * Fast, simple pseudo-random number generator providing 31 bits of entropy.
 */
public class QuickRandom {

    /**
     * Get the next (positive) random number which is at least 0 and less than N.
     *
     * @return the value of get(0).
     */
    public int get() {
        return get(0);
    }

    /**
     * Get the next random number which is at least m and less than N.
     *
     * @return a pseudo-random number between m (inclusive) and N (exclusive).
     */
    public int get(int m) {
        r ^= r << 13;
        r ^= r >> 17;
        r ^= r << 5;
        r &= 0x7FFFFFFF;
        return r % (N - m) + m;
    }

    public QuickRandom(int N, long seed) {
        this.N = N;
        r = (int) seed;
    }

    public QuickRandom(int N) {
        this(N, System.currentTimeMillis());
    }

    public QuickRandom(long seed) {
        this(Integer.MAX_VALUE, seed);
    }

    public QuickRandom() {
        this(Integer.MAX_VALUE);
    }


    private final int N;
    private int r;
}