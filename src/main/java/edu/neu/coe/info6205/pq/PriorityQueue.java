package edu.neu.coe.info6205.pq;

import java.util.Comparator;

public class PriorityQueue<K> {

    /**
     * Basic constructor that takes the max value, an actually array of elements, and a comparator.
     *
     * @param max        whether or not this is a Maximum Priority Queue as opposed to a Minimum PQ.
     * @param binHeap    a pre-formed array.
     * @param last       the number of elements in binHeap
     * @param comparator a comparator for the type K
     */
    public PriorityQueue(boolean max, Object[] binHeap, int last, Comparator<K> comparator) {

        this.max = max;
        this.comparator = comparator;
        this.last = last;
        //noinspection unchecked
        this.binHeap = (K[]) binHeap;
    }

    /**
     * Constructor which takes only the priority queue's maximum capacity and a comparator
     * @param n the desired maximum capacity.
     * @param max whether or not this is a Maximum Priority Queue as opposed to a Minimum PQ.
     * @param comparator a comparator for the type K
     */
    public PriorityQueue (int n, boolean max, Comparator<K> comparator) {

        this(max, new Object[n], 0, comparator);
    }

    /**
     * Constructor which takes only the priority queue's maximum capacity and a comparator
     * @param n the desired maximum capacity.
     * @param comparator a comparator for the type K
     */
    public PriorityQueue (int n, Comparator<K> comparator) {

        this(n, true, comparator);
    }

    /**
     * @return true if the current size is zero.
     */
    public boolean isEmpty() {
        return last == 0;
    }

    /**
     * @return the number of elements actually stored in this Priority Queue
     */
    public int size() {
        return last;
    }

    /**
     * Insert an element with the given key into this Priority Queue.
     *
     * @param key the value of the key to insert
     */
    public void insert(K key) {
        binHeap[++last] = key;
        swimUp(last);
    }

    /**
     * Take the root element from this Priority Queue.
     * If max is true, then the result will be the maximum element, else the minimum element.
     * NOTE that this method is called DelMax (or DelMin) in the book.
     * @return If max is true, then the maximum element, otherwise the minimum element.
     */
    public K take() {
        K result = binHeap[1];
        swap(1, last--);
        sink(1);
        binHeap[last+1] = null;
        return result;
    }

    /**
     * Sink the element at index k down
     */
    private void sink(@SuppressWarnings("SameParameterValue") int k) {
        int i = k;
        while (firstChild(i) <= last) {
            int j = firstChild(i);
            if (j < last && unordered(j, j + 1)) j++;
            if (!unordered(i, j)) break;
            swap(i, j);
            i = j;
        }
    }

    /**
     * Swim the element at index k up
     */
    private void swimUp(int k) {
        int i = k;
        while (i > 1 && unordered(parent(i), i)) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    /**
     * Exchange the values at indices i and j
     */
    private void swap(int i, int j) {
        K tmp = binHeap[i];
        binHeap[i] = binHeap[j];
        binHeap[j] = tmp;
    }

    /**
     * Compare the elements at indices i and j.
     * We expect the first index (the smaller one) to be greater than the second, assuming that max is true.
     * In this case, we return false.
     *
     * @param i the lower index, numerically
     * @param j the higher index, numerically
     * @return true if the values are out of order.
     */
    private boolean unordered(int i, int j) {
        return (comparator.compare(binHeap[i], binHeap[j]) > 0) ^ max;
    }

    /**
     * Get the index of the parent of the element at index k
     */
    private int parent(int k) {
        return k / 2;
    }

    /**
     * Get the index of the first child of the element at index k.
     * The index of the second child will be one greater than the result.
     */
    private int firstChild(int k) {
        return k * 2;
    }

    /**
     * The following methods are for unit testing ONLY!!
     */

    private K peek(int k) {
        return binHeap[k];
    }

    private boolean getMax() {
        return max;
    }

    private final boolean max;
    private final Comparator<K> comparator;
    private final K[] binHeap; // binHeap[i] is ith element of binary heap
    private int last; // number of elements in the binary heap

}
