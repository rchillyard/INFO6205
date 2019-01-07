package com.example;

import org.junit.Test;
import static org.junit.Assert.*;
public class SolutionTest {

    @Test
    public void test1(){
        int[] ar = {1,2,3,4,5,6,7,8,9};
        assertEquals(Solution.binarySearch(ar,0,ar.length-1,3),2);
    }
    
    @Test
    public void test2(){
        int[] ar = {1,2,3,4,5,6,7,8,9};
        assertEquals(Solution.binarySearch(ar,0,ar.length-1,11),-1);
    }
    
    @Test
    public void test3(){
        int[] ar = {1};
        assertEquals(Solution.binarySearch(ar,0,ar.length-1,1),0);
    }
    
    @Test
    public void test4(){
        int[] ar = {};
        assertEquals(Solution.binarySearch(ar,0,ar.length-1,1),-1);
    }
}
