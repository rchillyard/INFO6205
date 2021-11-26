package edu.neu.coe.info6205.threesum;

public interface TwoSum {
    /**
     * Method to get the pairs from this instance of TwoSum.
     * <p>
     * NOTE: it is essential that the array to be operated on is ordered and distinct.
     * The quadratic implementation of TwoSum doesn't care about order of distinction, however.
     *
     * @return an ordered, distinct, array of Pair.
     */
    Pair[] getPairs();
}
