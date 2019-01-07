package com.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SolutionTest {

  
    @Test
    public void test1(){
		Solution sol = new Solution();
		int[] ar = {1,0};
		assertEquals(0,sol.countTriplets(ar));
	}
    
    @Test
	public void test2(){
		Solution sol = new Solution();
		int[] ar = {-1,0,1,2,-1,-4};
		assertEquals(3,sol.countTriplets(ar));
		
	}
	
	@Test
	public void test3(){
		Solution sol = new Solution();
		int[] ar = {-1,0,1,2,-1,-4,5,10,11,3,-10,6,5,3,7,4,2,8,9,-5,4,5,-12,6};
		assertEquals(55,sol.countTriplets(ar));
		
	}
    
    @Test
	public void test4(){
		Solution sol = new Solution();
		int[] ar = {1,2,3,4,5,6,7,8};
		assertEquals(0,sol.countTriplets(ar));
		
	}
}
