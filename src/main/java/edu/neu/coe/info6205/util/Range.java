package edu.neu.coe.info6205.util;

public class Range {
    /**
     * Constructor
     *
     * @param low  the lowest number considered in range.
     * @param high the highest number considered in range.
     */
    public Range(int low, int high) {
        this.low = low;
        this.high = high;
    }

    /**
     * Method to determine if a number is in this Range.
     *
     * @param number the number to be tested.
     * @return true if the number is in this Range (inclusive).
     */
    public boolean contains(int number) {
        return (number >= low && number <= high);
    }

    public static Range valueOf(int low, int high) {
        return new Range(low, high);
    }

    private final int low;
    private final int high;
}