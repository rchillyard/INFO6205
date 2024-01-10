package edu.neu.coe.info6205;

import java.util.Arrays;

/**
 * Class to provide the functionality of binary search.
 * NOTE that it is essentially the same as the Arrays.binarySearch method.
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] ar = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int res1 = binarySearch(ar, 0, ar.length - 1, 3);
        System.out.println(res1);
        int res2 = Arrays.binarySearch(ar, 0, ar.length, 3);
        System.out.println(res2);
    }

    static int binarySearch(int[] a, int low, int to, int key) {
        int lo = low;
        int hi = to;
        while (hi > lo) {
            // TO BE IMPLEMENTED  : implement binary search








            // SKELETON CODE
             return -1;
            // END SOLUTION
        }
        return -1;
    }
}