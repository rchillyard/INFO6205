package edu.neu.coe.info6205.sort.simple;

public class InsertionSort<X extends Comparable<X>> implements Sort<X> {

    /**
     * Constructor for InsertionSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public InsertionSort(Helper<X> helper) {
        this.helper = helper;
    }

    public InsertionSort() {
        this(new Helper<>("InsertionSort"));
    }

    @Override
    public void sort(X[] xs, int from, int to) {
        // TO BE IMPLEMENTED ...
        for (int i = from; i < to; i++)
            for (int j = i; j > 0; j--)
                if (helper.less(xs[j], xs[j - 1]))
                    helper.swap(xs, from, to, j, j - 1);
                else break;
        // ... END IMPLEMENTATION
    }

    @Override
    public String toString() {
        return helper.toString();
    }

    @Override
    public Helper<X> getHelper() {
        return helper;
    }

    private final Helper<X> helper;
}
