package edu.neu.coe.info6205.sort.simple;

public class QuickSort_3way<X extends Comparable<X>> implements Sort<X> {
    /**
     * Constructor for InsertionSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public QuickSort_3way(Helper<X> helper) {
        this.helper = helper;
    }

    public QuickSort_3way() {
        this(new Helper<>("3-way QuickSort"));
    }

    class Partition {
        final int lt;
        final int gt;

        public Partition(int lt, int gt) {
            this.lt = lt;
            this.gt = gt;
        }
    }

    @Override
    public void sort(X[] a, int from, int to) {
        @SuppressWarnings("UnnecessaryLocalVariable") int lo = from;
        int hi = to - 1;
        if (hi <= lo) return;
        Partition partition = partition(a, lo, hi);
        sort(a, lo, partition.lt - 1);
        sort(a, partition.gt + 1, hi);
    }

    public Partition partition(X[] a, int lo, int hi) {
        int lt = lo, gt = hi;
        if (a[lo].compareTo(a[hi])>0) swap(a, lo,hi);
        X v = a[lo];
        int i = lo + 1;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) swap(a, lt++, i++);
            else if (cmp > 0) swap(a, i, gt--);
            else i++;
        }
        return new Partition(lt, gt);
    }

    // exchange a[i] and a[j]
    public static void swap(Object[] a, int i, int j) {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    @Override
    public Helper<X> getHelper() {
        return helper;
    }

    private final Helper<X> helper;
}

