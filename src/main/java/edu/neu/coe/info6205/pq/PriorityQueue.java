package edu.neu.coe.info6205.pq;

import java.util.*;

/**
 * Priority Queue Data Structure which uses a binary heap.
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
     * Basic constructor that takes the max value, an actually array of elements, and a comparator.
     *
     * @param max        whether or not this is a Maximum Priority Queue as opposed to a Minimum PQ.
     * @param binHeap    a pre-formed array with length one greater than the required capacity.
     * @param last       the number of elements in binHeap
     * @param comparator a comparator for the type K
     * @param floyd      true if we use Floyd's trick
     */
    public PriorityQueue(boolean max, Object[] binHeap, int last, Comparator<K> comparator, boolean floyd) {
        this.max = max;
        this.comparator = comparator;
        this.last = last;
        //noinspection unchecked
        this.binHeap = (K[]) binHeap;
        this.floyd = floyd;
    }

    /**
     * Constructor which takes only the priority queue's maximum capacity and a comparator
     *
     * @param n          the desired maximum capacity.
     * @param max        whether or not this is a Maximum Priority Queue as opposed to a Minimum PQ.
     * @param comparator a comparator for the type K
     */
    public PriorityQueue(int n, boolean max, Comparator<K> comparator, boolean floyd) {

        // NOTE that we reserve the first element of the binary heap, so the length must be n+1, not n
        this(max, new Object[n + 1], 0, comparator, floyd);
    }

    /**
     * Constructor which takes only the priority queue's maximum capacity and a comparator
     *
     * @param n          the desired maximum capacity.
     * @param max        whether or not this is a Maximum Priority Queue as opposed to a Minimum PQ.
     * @param comparator a comparator for the type K
     */
    public PriorityQueue(int n, boolean max, Comparator<K> comparator) {

        // NOTE that we reserve the first element of the binary heap, so the length must be n+1, not n
        this(n, max, comparator, false);
    }

    /**
     * Constructor which takes only the priority queue's maximum capacity and a comparator
     *
     * @param n          the desired maximum capacity.
     * @param comparator a comparator for the type K
     */
    public PriorityQueue(int n, Comparator<K> comparator) {

        this(n, true, comparator, true);
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
     * @param key the value of the key to give
     */
    public void give(K key) {
        if (last == binHeap.length - 1)
            last--; // if we are already at capacity, then we arbitrarily trash the least eligible element
        // (even if it's more eligible than key).
        binHeap[++last] = key; // insert the key into the binary heap just after the last element
        swimUp(last); // reorder the binary heap
    }

    /**
     * Remove the root element from this Priority Queue and adjust the binary heap accordingly.
     * If max is true, then the result will be the maximum element, else the minimum element.
     * NOTE that this method is called DelMax (or DelMin) in the book.
     *
     * @return If max is true, then the maximum element, otherwise the minimum element.
     * @throws PQException if this priority queue is empty
     */
    public K take() throws PQException {
        if (isEmpty()) throw new PQException("Priority queue is empty");
        if (!floyd) {
            K result = binHeap[1]; // get the root element (the largest or smallest, according to field max)

            swap(1, last--); // swap the root element with the last element

            sink(1); // adjust the heap so that it is ordered again

            binHeap[last + 1] = null; // prevent loitering

            return result;
        } else {
            K result = binHeap[1];
            swap(1, last--);
            snake(1);
            binHeap[last + 1] = null;
            return result;
        }
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

    //Special sink method that sink the element and then swim the element back
    private void snake(@SuppressWarnings("SameParameterValue") int k) {
        int i = k;
        while (firstChild(i) <= last) {
            int j = firstChild(i);
            if (j < last && unordered(j, j + 1)) j++;

            swap(i, j);
            i = j;
        }
        swimUp(i);

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

    @SuppressWarnings("unused")
    private K peek(int k) {
        return binHeap[k];
    }

    @SuppressWarnings("unused")
    private boolean getMax() {
        return max;
    }

    private final boolean max;
    private final Comparator<K> comparator;
    private final K[] binHeap; // binHeap[i] is ith element of binary heap (first element is reserved)
    private int last; // number of elements in the binary heap
    private final boolean floyd; //Determine whether floyd's snake method is on or off inside the take method

    @Override
    public Iterator<K> iterator() {
        Collection<K> result = new ArrayList<>(Arrays.asList(binHeap));
        return result.iterator();
    }

    public static void main(String[] args) {
        String[] s1 = new String[5]; //Created a string type array with size 5
        s1[0] = "A";
        s1[1] = "B";
        s1[2] = "C";
        s1[3] = "D";
        s1[4] = "E";
        boolean max = true;
        boolean floyd = true;
        PriorityQueue<String> PQ_string_floyd = new PriorityQueue<>(max, s1, 5, Comparator.comparing(String::toString), floyd);
        PriorityQueue<String> PQ_string_nofloyd = new PriorityQueue<>(max, s1, 5, Comparator.comparing(String::toString), false);
        Integer[] s2 = new Integer[5]; //created an Integer type array with size 5
        for (int i = 0; i < 5; i++) {
            s2[i] = i;
        }
        PriorityQueue<Integer> PQ_int_floyd = new PriorityQueue<>(max, s2, 5, Comparator.comparing(Integer::intValue), floyd);
        PriorityQueue<Integer> PQ_int_nofloyd = new PriorityQueue<>(max, s2, 5, Comparator.comparing(Integer::intValue), false);


    }
}
