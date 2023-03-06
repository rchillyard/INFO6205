package edu.neu.coe.info6205.util;

import java.util.Random;

public class QuickSelect {

    public static void main (String[] args) {
        int[] array = new int[] {6, 10, 12, 42, 79, 81, 98};//{6, 42, 31, 2, 4, 79, 81, 9, 23, 98};
        //knuthShuffle(array);
        Random random = new Random();
        int newIndex = random.nextInt(array.length);
        partition(array, 0, array.length - 1);

        //System.out.println("find "+newIndex+" element in array : element"+ quickSelect(array, newIndex));
        print(array);
    }


    public static int quickSelect(int[] array, int k) {

        int from = 0;
        int to = array.length - 1;
        int j = 0;
        while (from < to) {
            j = partition(array, from, to);
            if (j < k) {
                from = j + 1;
            } else if (j > k) {
                to = j - 1;
            } else {
                return array[k];
            }
        }
        return array[k];

    }

    public static int partition(int[] array, int start , int end) {
        if (start >= end) {
            return -1;
        }
        swap(array, start, 5);

        int val = array[start];
        System.out.println("pivot :"+val);
        int i = start + 1;

        while (i <= end) {

            while (array[i] < val) {
                i++;
            }
            while (array[end] > val) {
                end--;
            }
            if (i > end)
                break;
            swap(array, i++, end--);
        }
        swap(array, i - 1, start);

        return i - 1;



    }

    public static void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
        System.out.println();
    }


    public static void knuthShuffle(int[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            int newIndex = random.nextInt(i + 1);
            swap(array, newIndex, i);
        }
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
