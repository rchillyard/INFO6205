package edu.neu.coe.info6205.sort.simple;

public class Helper {

    static <X extends Comparable<X>> boolean less(X v, X w) {
        return v.compareTo(w) < 0;
    }

    static <X extends Comparable<X>> void swap(X[] a, int lo, int hi, int i, int j) {
        if (i<lo) throw new RuntimeException("i is out of range: i; "+i+"; lo="+lo);
        if (j>hi) throw new RuntimeException("j is out of range: j; "+j+"; hi="+hi);
        X temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
