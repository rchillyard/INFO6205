package com.example;

import java.util.Arrays;

public class Solution {

  // This class should not be instantiated.
  private Solution() {
  }

  /**
   * Rearranges the array in ascending order, using the natural order.
   * 
   * @param A the array to be sorted
   */
  public static void sort(Comparable[] A) {
      // TODO
      int N = A.length;
      for(int k=N/2; k>=1; k--) {
          sink(A, k, N);
      }
      while(N > 1) {
          swap(A, 1, N--);
          sink(A, 1, N);
      }
  }
  /**
   * 
   * @param A the array to be sorted
   * @param k the index of the element you want to sink in a 1-based index array.
   * @param n the last index of the element in a 1-based index array.
   */
  public static void sink(Comparable[] A, int k, int n) {
      // TODO
      while(k*2 <= n) {
          int j = k*2;
          if(j < n && less(A, j, j+1))
              j++;
          if(!less(A, k, j)) break;
          swap(A, k, j);
          k=j;
      }
  }
    

    
  /**
  *you can either choose to modify or not to modify this method
  */
  public static boolean less(Comparable[] A, int i, int j) {
      return A[i-1].compareTo(A[j-1]) < 0;

  }
  /**
  *you can either choose to modify or not to modify this method
  */
  public static void swap(Object[] A, int i, int j) {
      Comparable tmp = (Comparable) A[i-1];
      A[i-1] = A[j-1];
      A[j-1] = tmp;
  }

  public static void main (String args[]){
    Integer[] A = {2,3,4,1,2,5};
    Solution.sort(A);

  }

}