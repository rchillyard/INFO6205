package com.example;

public class Solution {

  public static void main (String args[]){
      int[] ar = {1,2,3,4,5,6,7,8,9};
      int res = Solution.binarySearch(ar,0,ar.length-1,3);
      System.out.println(res);
      

  }
    

    static int binarySearch(int a[], int lo, int hi, int key)
    {
        //TODO:: implement binary search
        if(lo > hi) return -1;
        
        int mid = lo + (hi - lo) / 2;
        
        if(key == a[mid]) return mid;
        else if(key > a[mid]) {
            return binarySearch(a, mid + 1, hi, key);
        }
        else {
            return binarySearch(a, lo, mid - 1, key);
        }
  
    }
    
    
}
