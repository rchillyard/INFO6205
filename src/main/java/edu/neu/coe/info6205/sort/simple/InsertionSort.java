package edu.neu.coe.info6205.sort.simple;

import static edu.neu.coe.info6205.sort.simple.Helper.*;

public class InsertionSort<X> implements Sort<X> {
    @Override
    public void sort(Comparable<X>[] xs) {
        int N = xs.length;
        for (int i = 0; i < N; i++)
            for (int j = i; j > 0; j--)
                if (less(xs[j], xs[j-1]))
                    swap(xs, j, j-1);
                else break;
    }
}
