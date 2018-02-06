package edu.neu.coe.info6205.sort.simple;

import static edu.neu.coe.info6205.sort.simple.Helper.less;
import static edu.neu.coe.info6205.sort.simple.Helper.swap;

public class SelectionSort<X> implements Sort<X> {
    @Override
    public void sort(Comparable<X>[] xs) {
        int N = xs.length;
        for (int i = 0; i < N; i++)
        {
            int min = i;
            for (int j = i+1; j < N; j++)
            if (less(xs[j], xs[min]))
                min = j;
            swap(xs, i, min);
        }
    }

    @Override
    public void sort(Comparable<X>[] xs, int from, int to) {
        for (int i = from; i < to; i++)
        {
            int min = i;
            for (int j = i+1; j < to; j++)
                if (less(xs[j], xs[min]))
                    min = j;
            swap(xs, i, min);
        }
    }
}
