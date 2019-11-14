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
        for (int i = from; i < to; i++)
            // Invariant 1: elements xs[from..i] are in order
            // TO BE IMPLEMENTED ...

				// NOTE: this is the simple, slow way as originally described.
//            for (int j = i; j > 0; j--)
//                if (helper.less(xs[j], xs[j - 1]))
//                    helper.swap(xs, from, to, j);
//                else break;

				// NOTE: this is the (2x) improvement called "half exchanges"
				{
						X x = xs[i];
						int j = i;
						while (j > 0 && helper.less(x, xs[j - 1])) {
								// NOTE: can use helper.moveUp but it takes somewhat longer
								xs[j] = xs[j - 1];
								j--;
						}
						xs[j] = x;
				}
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
