package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class MergeSortTest {
	@Test
    public void sortTest0(){
		Integer[] test = {5,2,3,4,2,3,4,1};
		Integer[] result = MergeSort.sort(test);
		Integer[] answer = {1,2,2,3,3,4,4,5};
		assertArrayEquals(answer,result);
    }
	
	@Test
    public void sortTest1(){
		Integer[] test = {1,2,3,4,2,-3,-4,-5};
		Integer[] result = (Integer[]) MergeSort.sort(test);
		Integer[] answer = {-5,-4,-3,1,2,2,3,4};
		assertArrayEquals(answer,result);
    }
    
    @Test
    public void sortTest2(){
		Integer[] test = {1,2,3,4,5,6,9,10};
		Integer[] result = (Integer[]) MergeSort.sort(test);
		Integer[] answer = {1,2,3,4,5,6,9,10};
		assertArrayEquals(answer,result);
    }
	
	@Test
    public void sortTest3(){
		Integer[] test = {10,9,6,5,4,3,2,1};
		Integer[] result = (Integer[]) MergeSort.sort(test);
		Integer[] answer = {1,2,3,4,5,6,9,10};
		assertArrayEquals(answer,result);
    }
	
	@Test
    public void sortTestSingleton(){
		Integer[] test = {10};
		Integer[] result = (Integer[]) MergeSort.sort(test);
		Integer[] answer = {10};
		assertArrayEquals(answer,result);
    }
	
	@Test
    public void sortTestEmpty(){
		Integer[] test = {};
		Integer[] result = (Integer[]) MergeSort.sort(test);
		Integer[] answer = {};
		assertArrayEquals(answer,result);
    }
    
    @Test
    public void testSortedArray(){
		//before sort
		Integer[] test = {1,10,-2,9,0};
		assertTrue(test[0]==1);
		
		Integer[] result = MergeSort.sort(test);
		Integer[] answer =  {-2,0,1,9,10};
		
		//after sort
		assertTrue(test[0]==-2);
		assertTrue(test[1]==0);
		assertTrue(test[2]==1);
		assertTrue(test[3]==9);
		assertTrue(test[4]==10);
    }
    
}