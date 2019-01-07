package com.example;
import java.util.Arrays;

public class Solution {

  public static void main (String args[]){
      int[] ar = {1,2,3,4,5,6,7,8,9};
      int res = Solution.binarySearch(ar,0,ar.length-1,3);
      System.out.println(res);
      

  }
    

    static int binarySearch(int a[], int lo, int hi, int key)
    {
        //TODO:: implement binary search
        while(lo<=hi)
        {
        int mid=lo+(hi-lo)/2;
            
        if(key<a[mid]) hi=mid-1;
        else if(key>a[mid]) lo=mid+1;
        else return mid;
        }
              return -1;
  
    }
    
    
}
