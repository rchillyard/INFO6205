package com.info6205;

import java.util.ArrayList;
import java.util.List;

public class Insertion<X extends Comparable<X>> {

  int swaps = 0;
  /**
   * com.info6205.Insertion sort method that mutate array
   *
   * @param xs   sort the array xs
   */
  public void sort(X[] xs) {
    // TO BE IMPLEMENTED ...
    // ... END IMPLEMENTATION
      int n = xs.length;
      
      for(int i=0 ; i< n ; i++){
          for(int j = i ; j> 0;j -- ){
              if(less(xs[j],xs[j-1])){
                  swap(xs,j,j-1);
                  
              }
              
              
          }
          
          
      }
      
      
  }

  /**
   * Method to determine if one X value is less than another.
   *
   * @param v   the candidate element.
   * @param w   the comparand element.
   * @return true only if v is less than w.
   */
  private boolean less(X v, X w) {
    return v.compareTo(w) < 0;
  }

  /**
   * Swap the elements of array a at indices i and j.
   *
   * @param a   the array.
   * @param i   one of the indices.
   * @param j   the other index.
   */
  public void swap(X[] a, int i, int j) {
    swaps++;
      X t = a[i];
      a[i]= a[j];
      a[j]= t;
      
    // TO BE IMPLEMENTED ...
    // ... END IMPLEMENTATION
  }
}
