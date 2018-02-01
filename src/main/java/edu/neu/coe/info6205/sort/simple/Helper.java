package edu.neu.coe.info6205.sort.simple;

public class Helper {

    static boolean less(Comparable v, Comparable w)
    {  return v.compareTo(w) < 0;  }

    static void swap(Comparable[] a, int i, int j)
    {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
