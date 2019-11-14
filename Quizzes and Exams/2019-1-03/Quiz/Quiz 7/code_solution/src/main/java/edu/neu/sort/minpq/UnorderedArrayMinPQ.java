package edu.neu.sort.minpq;

public class UnorderedArrayMinPQ<Key extends Comparable<Key>> {

    private Key[] pq;      // elements
    private int n;         // number of elements

    // set inititial size of heap to hold size elements
    public UnorderedArrayMinPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void insert(Key x) {
       // To be implemented
        pq[++n] = x;
    }

    public Key delMin() {
    	//To be implemented
        if(!isEmpty()){
            Key min = pq[1];
            int minIndex = 1;
            for(int i=1; i<= n ; i++){
                if(less(i, minIndex)){
                    min = pq[i];
                    minIndex = i;
                }
            }
            swap(minIndex, n);
            n--;
            pq[n+1] = null;
            return min;
        }
        return null;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void swap(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }
}
