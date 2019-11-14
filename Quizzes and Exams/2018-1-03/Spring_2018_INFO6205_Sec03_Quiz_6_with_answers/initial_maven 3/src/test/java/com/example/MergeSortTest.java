package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class MergeSortTest {
	@Test
    public void test0(){
		Integer[] test = {5,2,3,4,2,3,4,1};
		Integer[] result = MergeSort.sort(test);
		Integer[] answer = {1,2,2,3,3,4,4,5};
		assertArrayEquals(result,answer);
    }
	
	@Test
    public void test1(){
		Integer[] test = {1,2,3,4,2,-3,-4,-5};
		Integer[] result = (Integer[]) MergeSort.sort(test);
		Integer[] answer = {-5,-4,-3,1,2,2,3,4};
		assertArrayEquals(result,answer);
    }
    
    @Test
    public void test2(){
		Integer[] test = {1,2,3,4,5,6,9,10};
		Integer[] result = (Integer[]) MergeSort.sort(test);
		Integer[] answer = {1,2,3,4,5,6,9,10};
		assertArrayEquals(result,answer);
    }
	
	@Test
    public void test3(){
		Integer[] test = {10,9,6,5,4,3,2,1};
		Integer[] result = (Integer[]) MergeSort.sort(test);
		Integer[] answer = {1,2,3,4,5,6,9,10};
		assertArrayEquals(result,answer);
    }
	
	@Test
    public void test4(){
		Integer[] test = {10};
		Integer[] result = (Integer[]) MergeSort.sort(test);
		Integer[] answer = {10};
		assertArrayEquals(result,answer);
    }
	
	@Test
    public void test5(){
		Integer[] test = {};
		Integer[] result = (Integer[]) MergeSort.sort(test);
		Integer[] answer = {};
		assertArrayEquals(result,answer);
    }

    // This can be used to test the merge function. You can uncomment and run it if you wish.    
    /*@Test
    public void test6(){
		Integer[] a = {1,2,3,-5};
		Integer[] aux = new Integer[a.length];
		int n = a.length;
		for (int len = 1; len < n; len *= 2) {
            for (int lo = 0; lo < n-len; lo += len+len) {
                int mid  = lo+len-1;
                int hi = Math.min(lo+len+len-1, n-1);
                MergeSort.merge(a, aux, lo, mid, hi);
            }
        }
		Integer[] answer = {-5,1,2,3};
		for(int i : a) System.out.print(i);
		assertArrayEquals(a,answer);
	}*/
    
    @Test
    public void testSortedArray(){
		//before sort
		Integer[] test = {1,10,-2,9,0};
		assertTrue(test[0]==1);
		
		Integer[] result = MergeSort.sort(test);
		Integer[] answer =  {-2,0,1,9,10};;
		
		//after sort
		assertTrue(test[0]==-2);
		assertTrue(test[1]==0);
		assertTrue(test[2]==1);
		assertTrue(test[3]==9);
		assertTrue(test[4]==10);
    }
    
}