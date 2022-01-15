package edu.neu.coe.info6205.sort.elementary;

public class Solution {
    public static void main(String[] args) {
        int[] array = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int res = Solution.binarySearch(array, 3);
        System.out.println(res);
    }

    static int binarySearch(int[] array, int key) {
        //TODO:: implement binary search
        if (array.length == 0) return -1;
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int temp = (left + right) / 2;
            if (array[temp] == key)
                return temp;
            else if (array[temp] > key) {
                left = temp + 1;
            } else if (array[temp] < key) {
                right = temp - 1;
            }
        }
        return -1;

    }


}