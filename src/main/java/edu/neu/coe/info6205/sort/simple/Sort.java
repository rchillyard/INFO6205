package edu.neu.coe.info6205.sort.simple;

public interface Sort<X> {

    /**
     * Generic sort method.
     *
     * @param xs sort the array xs in place
     */
    public void sort(Comparable<X>[] xs);
}
