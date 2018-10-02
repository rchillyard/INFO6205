package edu.neu.coe.info6205.sort.simple;

import java.util.*;

public class ShellSort<X extends Comparable<X>> {

    /**
     * Constructor for ShellSort
     *
     * @param m the "gap" (h) sequence to follow:
     *          1: ordinary insertion sort;
     *          2: use powers of two less one;
     *          3: use the sequence in the book: 1, 4, 13, etc.
     */
    public ShellSort(int m) {
        this.m = m;
    }

    /**
     * Method to sort an array of Xs
     * @param array an array of Xs to be sorted in place.
     */
    public void sort(X[] array) {
        int N = array.length;
        H hh = new H(N);
        int h = hh.next();
        while (h > 0) {
            for (int i = h; i < N; i++)
                for (int j = i; j >= h && Helper.less(array[j], array[j - h]); j -= h) {
                    Helper.swap(array, j, j - h);
                    System.out.println("after step: h=" + h + ", i=" + i + ":\t" + Arrays.toString(array));
                }
            h = hh.next();
        }
    }

    private final int m;

    private class H {
        private int h = 1;
        private boolean started = false;

        H(int N) {
            switch (m) {
                case 1:
                    h = 1;
                    break;
                case 2:
                    while (h <= N) h = 2 * (h + 1) - 1;
                    break;
                case 3:
                    while (h <= N / 3) h = h * 3 + 1;
                    break;
                default:
                    throw new RuntimeException("invalid m value: " + m);
            }
        }

        public int next() {
            if (started) {
                switch (m) {
                    case 1:
                        return 0;
                    case 2:
                        h = (h + 1) / 2 - 1;
                        return h;
                    case 3:
                        h = h / 3;
                        return h;
                    default:
                        throw new RuntimeException("invalid m value: " + m);
                }
            } else {
                started = true;
                return h;
            }
        }
    }

    public static void main(String[] args) {
        ShellSort<Integer> s = new ShellSort<>(2);
        Integer[] array = {5, 3, 0, 2, 4, 1, 0, 5, 2, 3, 1, 4};
        System.out.println("Before:\t\t\t\t\t" + Arrays.toString(array));
        s.sort(array);
    }
}
