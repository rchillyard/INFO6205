package com.example;
import java.util.Random;

public class Solution {

    public int countTriplets(int[] a){
        int count = 0;
      
        //write your code here
        //run unit test to verify the logic
        //execute main to check the time taken for execution
        for (int x = 0; x < a.length; x++)
            for (int y = x + 1; y < a.length; y++)
                for (int z = y + 1; z < a.length; z++){
                    if (a[x] + a[y] + a[z] == 0)
                        count++;
                }
        return count;
    }
    
  
    public static void main (String args[]){
      Solution sol = new Solution();
      Random random = new Random();
      int len=100;
      for(int j=0 ; j<4 ; j++) //This for loop ensure doubling method
      {
          len = 2*len;
          int[] ar = new int[len];
          int result=0;
          long ex_time=0;
          for(int k=0;k<5;k++) //This for loop is for averaging out the results 
          {
            for(int i = 0;i<ar.length;i++)//Intializing Array with random elements
            {
                ar[i] = random.nextInt();
            }
            long begin = System.currentTimeMillis();  
            result = result+sol.countTriplets(ar);
            long end = System.currentTimeMillis();
            ex_time=ex_time+(end - begin); 
          }
          System.out.println("Average Number of triplets in array of length "+len+"is : "+ result/5);
          System.out.println("Average Run time(ms) : "+ex_time/5);
      }
      
  }
 
}
