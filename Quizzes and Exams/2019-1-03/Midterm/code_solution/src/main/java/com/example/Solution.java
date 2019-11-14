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
    * overloaded sort method for QuickSort
    *
    * */
    private  void sort(List<T> a, int lo, int hi)
    {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }


    /*
    *
    * Implement the partition method that determines the pivot
    * element.
    * partitioning scheme: 
    * pivot is chosen always as the last element of the List.
    *
    * */
    public  int partition(List<T> a, int lo, int hi)
    {

        int i = lo - 1;
        int j = hi;
        T pivot = a.get(hi);
        while(true){  
            while(less(a.get(++i), pivot))
                if(i == hi) break;
            
            while(less(pivot, a.get(--j)))
                if(j == lo) break;
            
            if(i >= j) break;
            exch(a, i, j);  
        }
        
        exch(a, hi, i);
        return i;
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
