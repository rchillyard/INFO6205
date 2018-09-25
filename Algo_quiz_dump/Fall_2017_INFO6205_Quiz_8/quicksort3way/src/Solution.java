import java.io.*;
import java.util.Scanner;

public class Solution {
    private static int CUTOFF = 10;
    // This class should not be instantiated.

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(int[] a) {
        sort(a, 0, a.length - 1);
    }
    private static void sort(int[] a, int lo, int hi) {
        //TODO:: Implement the insertion sort cut off
        if(hi <= (lo + CUTOFF)) {
            insertionSort(a, lo, hi);
            return;
        }
        //END of TODO

        // TODO::Use the partitioning to do quicksort
        int lt = lo, gt = hi;
        int v = a[lo];
        int i = lo;
        while (i <= gt) {
            if      (a[i] < v) {
                exch(a, lt++, i++);
            }
            else if (a[i] > v) {
                exch(a, i, gt--);
            }
            else              i++;
            sort(a, lo, lt -1);
            sort(a, gt + 1, hi);
        }




        //END of TODO
    }
    public static void insertionSort(int[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
    }

    // is v < w ?
    private static boolean less(int v, int w) {
        return v - w < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner in = new Scanner(System.in);
        int sz = in.nextInt();
        int[] a = new int[sz];
        for(int i = 0; i < sz; i++) {
            a[i] = in.nextInt();
        }
        sort(a);
        for(int i = 0; i < sz; i++) {
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }
}