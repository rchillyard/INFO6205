package edu.neu.coe.info6205.sort.elementary;

import java.util.Arrays;

/**
 * This is a basic implementation of insertion sort.
 * It does not extend Sort, nor does it employ any optimizations.
 */
public class InsertionSortBasic {

    public void sort(Comparable<?>[] a) {
        sort(a, 0, a.length);
    }

    public void sort(Comparable<?>[] a, int lo, int hi) {
        for (int i = lo + 1; i < hi; i++) swap(i, a);
    }

    private void swap(int i, Object[] a) {
        // TO BE IMPLEMENTED 
throw new RuntimeException("implementation missing");
    }

    private void swap(Object[] a, int j, int i) {
        Object temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }

    public static void main(String[] args) {
        InsertionSortBasic sorter = new InsertionSortBasic();
        System.out.println(Arrays.toString(args));
        sorter.sort(args);
        System.out.println(Arrays.toString(args));
    }
}