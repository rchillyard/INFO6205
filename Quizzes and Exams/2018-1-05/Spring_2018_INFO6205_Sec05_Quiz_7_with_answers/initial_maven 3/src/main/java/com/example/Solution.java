package com.example;
import java.util.*;

public class Solution<T extends Comparable> {

    /*
    * This method accepts a list of elements of generic type T
    * which are comparable
    * */
    public void sort(List<T> a)
    {
        sort(a, 0, a.size() - 1);
    }


    /*
    * Implement overloaded sort method for QuickSort
    *
    * */
    private void sort(List<T> a, int lo, int hi)
    {
        if (lo < hi)
        {
            int pi = partition(a, lo, hi);
 
            sort(a, lo, pi-1);
            sort(a, pi+1, hi);
        }        
    }


    /*
    *
    * Implement the partition method that determines the pivot
    * element
    *
    * */
    private int partition(List<T> a, int low, int high)
    {
        int mid = (Integer)a.get(high); 
        int i = (low-1); 
        for (int j=low; j<high; j++)
        {
            int z = (Integer)a.get(j);
            if (z <= mid)
            {
                i++;
                exch(a, i, j);                    
            }
        }
        exch(a, i+1, high);
 
        return i+1;
    }

    /*
    * swaps two elements based on index i and j
    *
    * */
    private  void exch(List<T> arr, int i, int j) {
        T temp = arr.get(i);

        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    /*
    * compares two comparable elements and returns
    * true if element a is less than b , else false
    * */
    private  boolean less(T a, T b) {
        if(a.compareTo(b) < 0) {
            return true;
        }
        return false;
    }    
 
}
