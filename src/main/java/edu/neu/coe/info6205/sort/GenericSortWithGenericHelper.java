package edu.neu.coe.info6205.sort;

import edu.neu.coe.info6205.util.Config;

/**
 * Abstract class GenericSortWithGenericHelper which extends GenericSort.
 *
 * @param <X> the underlying type which does not have to be Comparable.
 */
public abstract class GenericSortWithGenericHelper<X> implements GenericSort<X> {

    public GenericSortWithGenericHelper(GenericHelper<X> helper) {
        this.helper = helper;
    }

    public GenericSortWithGenericHelper(String description, int N, Config config) {
        this(HelperFactory.createGeneric(description, N, config));
        closeHelper = true;
    }

    /**
     * Get the Helper associated with this Sort.
     *
     * @return the Helper
     */
    public GenericHelper<X> getHelper() {
        return helper;
    }

    @Override
    public String toString() {
        return helper.toString();
    }

    public void close() {
        if (closeHelper) helper.close();
    }

    private final GenericHelper<X> helper;
    protected boolean closeHelper = false;

}