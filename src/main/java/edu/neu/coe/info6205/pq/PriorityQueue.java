package edu.neu.coe.info6205.pq;

import java.util.*;

/**
 * Priority Queue Data Structure which uses a d-way heap.
 * <p>
 * It is unlimited in capacity, although there is no code to grow it after it has been constructed.
 * It can serve as a minPQ or a maxPQ (define "max" as either false or true, respectively).
 * <p>
 * It follows the code from Sedgewick and Wayne more or less. I have changed the names a bit. For example,
 * the methods to insert and remove the max (or min) element are called "give" and "take," respectively.
 * <p>
 * It operates on arbitrary Object types which implies that it requires a Comparator to be passed in.
 * <p>
 * For all details on usage, please see PriorityQueueTest.java
 *
 * @param <K>
 */
public class PriorityQueue<K> implements Iterable<K> {

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
     * @param key the value of the key to give
     */
    public void give(K key) {
        if (last == dHeap.length - 1)
            last--; // if we are already at capacity, then we arbitrarily trash the least eligible element
        // (even if it's more eligible than key).
        dHeap[++last] = key; // insert the key into the heap just after the last element
        swimUp(last); // reorder the d-way heap
    }

    /**
     * Remove the root element from this Priority Queue and adjust the d-way heap accordingly.
     * If max is true, then the result will be the maximum element, else the minimum element.
     * NOTE that this method is called DelMax (or DelMin) in the book.
     *
     * @return If max is true, then the maximum element, otherwise the minimum element.
     * @throws PQException if this priority queue is empty
     */
    public K take() throws PQException {
        if (isEmpty()) throw new PQException("Priority queue is empty");
        K result = dHeap[1]; // get the root element (the largest or smallest, according to field max)
        swap(1, last--); // swap the root element with the last element
        sink(1); // adjust the heap so that it is ordered again
        dHeap[last + 1] = null; // prevent loitering
        return result;
    }

    /**
     * Basic constructor that takes the max value, an actual array of elements, and a comparator.
     *  @param max        whether or not this is a Maximum Priority Queue as opposed to a Minimum PQ.
     * @param dHeap    a pre-formed array with length one greater than the required capacity.
     * @param d     the max number of children for a node.
     * @param last       the number of elements in d-way heap.
     * @param comparator a comparator for the type K.
     */
    public PriorityQueue(boolean max, Object[] dHeap, int d, int last, Comparator<K> comparator) {
        this.max = max;
        this.d = d;
        this.comparator = comparator;
        this.last = last;
        //noinspection unchecked
        this.dHeap = (K[]) dHeap;
    }

    /**
     * Constructor which takes only the priority queue's maximum capacity and a comparator
     *  @param m          the desired maximum capacity.
     * @param max        whether or not this is a Maximum Priority Queue as opposed to a Minimum PQ.
     * @param d     the max number of children for a node.
     * @param comparator a comparator for the type K
     */
    public PriorityQueue(int m, boolean max, int d, Comparator<K> comparator) {
        // NOTE that we reserve the first element of the d-way heap, so the length must be m+1, not m
        this(max, new Object[m + 1], d, 0, comparator);
    }

    /**
     * Constructor which creates a binary-heap-based PQ and takes only the PQ's maximum capacity and a comparator.
     *
     * @param n          the desired maximum capacity.
     * @param comparator a comparator for the type K
     */
    public PriorityQueue(int n, Comparator<K> comparator) {
        this(n, true, 2, comparator);
    }

    /**
     * Sink the element at index k down
     */
    private void sink(@SuppressWarnings("SameParameterValue") int k) {
        int i = k;
        while (lthChild(i, 1) <= last) {
            int j = designatedChild(i);
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
        K tmp = dHeap[i];
        dHeap[i] = dHeap[j];
        dHeap[j] = tmp;
    }

    private int designatedChild(int k) {
        int first = k * d;
        int result = first;
        for (int i = 0; i < d; i++) if (unordered(result, first+i)) result = first+i;
        return result;
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
        return (comparator.compare(dHeap[i], dHeap[j]) > 0) ^ max;
    }

    /**
     * Get the index of the parent of the element at index k
     */
    private int parent(int k) {
        return k / d;
    }

    /**
     * Get the index of the l-th child of the element at index k.
     */
    private int lthChild(int k, int l) {
        return k * d + l;
    }

    /**
     * The following methods are for unit testing ONLY!!
     */

    @SuppressWarnings("unused")
    private K peek(int k) {
        return dHeap[k];
    }

    @SuppressWarnings("unused")
    private boolean getMax() {
        return max;
    }

    private final int d;
    private final boolean max;
    private final Comparator<K> comparator;
    private final K[] dHeap; // dHeap[i] is ith element of d-way heap (first element is reserved)
    private int last; // number of elements in the d-way heap

    @Override
    public Iterator<K> iterator() {
        Collection<K> result = new ArrayList<>(Arrays.asList(dHeap));
        return result.iterator();
    }
}
