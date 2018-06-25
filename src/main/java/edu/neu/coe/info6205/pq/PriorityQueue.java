package edu.neu.coe.info6205.pq;

import java.util.Arrays;
import java.util.Comparator;

public class PriorityQueue<K> {

    private final boolean max;
    private final Comparator<K> comparator;
    private final K[] binHeap; // binHeap[i] is ith element of binary heap
    private int last; // number of elements in the binary heap

    public PriorityQueue(boolean max, Object[] binHeap, Comparator<K> comparator) {

        this.max = max;
        this.comparator = comparator;
        this.last = 0;
        this.binHeap =  (K[])binHeap;

    }

    public PriorityQueue (int n, boolean max, Comparator<K> comparator) {

        this(max, new Object[n], comparator);
    }

    public PriorityQueue (int n, Comparator<K> comparator) {

        this(n, true, comparator);
    }

    public boolean isEmpty() {
        return last == 0;
    }

    public void insert(K key) {
        binHeap[++last] = key;
        swimUp(last);
    }

    public int size() {
        return last;
    }

    public K take() {
        K result = binHeap[1];
        swap(1, last--);
        sink(1);
        binHeap[last+1] = null;
        return result;
    }

    private void sink(int i) {
        int k = i;
        while (firstChild(k) <= last) {
            int j = firstChild(k);
            if (j < last && unordered(j, j+1)) j++;
            if (!unordered(k, j)) break;
            swap(k, j);
            k = j;
        }
    }

    private void swimUp(int k) {
        while (k > 1 && unordered(parent(k),k)) {
            swap(k, parent(k));
            k = parent(k);
        }
    }

    private void swap(int i, int j) {
        K tmp = binHeap[i];
        binHeap[i] = binHeap[j];
        binHeap[j] = tmp;
    }

    private boolean unordered(int i, int j) {
        return (comparator.compare(binHeap[i],binHeap[j])<0) ^ max;
    }

    private int parent(int k) {
        return k/2;
    }

    private int firstChild(int k) {
        return k*2;
    }

    /**
     * For testing only.
     * @param k
     * @return
     */
    private K peek(int k) {
        return binHeap[k];
    }
}
