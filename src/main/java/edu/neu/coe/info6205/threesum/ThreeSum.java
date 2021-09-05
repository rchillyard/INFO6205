package edu.neu.coe.info6205.threesum;

public interface ThreeSum {
    /**
     * Method to get the triples from this instance of ThreeSum.
     * <p>
     * NOTE: it is essential that the array to be operated on is ordered and distinct.
     * The cubic implementation of ThreeSum doesn't care about order of distinction, however.
     *
     * @return an ordered, distinct, array of Triple.
     */
    Triple[] getTriples();
}
