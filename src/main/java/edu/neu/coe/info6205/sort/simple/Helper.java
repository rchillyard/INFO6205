package edu.neu.coe.info6205.sort.simple;

public class Helper {

    static <X extends Comparable<X>> boolean less(X v, X w) {
        return v.compareTo(w) < 0;
    }

    static <X extends Comparable<X>> void swap(X[] a, int i, int j) {
        X temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
