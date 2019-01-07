package com.example;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SolutionTest {

    Solution<Integer> qs = null;

    @Before
    public void init() {
        qs = new Solution<>();
    }

    @Test
    public void quickSortReverseSortedTest() {


        List<Integer> inputList = Arrays.asList(10,9,8,7);
        List<Integer> expectedList = Arrays.asList(7,8,9,10);
        qs.sort(inputList);

        Assert.assertEquals(expectedList,inputList);
    }


    @Test
    public void quickSortPartialReverseTest() {

        List<Integer> inputList = Arrays.asList(1,2,3,9,8,7,10,4,5,6);
        List<Integer> expectedList =   Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        qs.sort(inputList);
        Assert.assertEquals(expectedList,inputList);
    }


    @Test
    public void quickSortSortedTest() {

        List<Integer> inputList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        List<Integer> expectedList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        qs.sort(inputList);
        Assert.assertEquals(expectedList,inputList);
    }

    @Test
    public void quickSortedDuplicateElementsTest() {
        List<Integer> inputList = Arrays.asList(7,4,1,2,3,3,7,9,8);
        List<Integer> expectedList = Arrays.asList(1,2,3,3,4,7,7,8,9);
        qs.sort(inputList);
        Assert.assertEquals(expectedList,inputList);
    }

    @Test
    public void quickSortRandomArray() {

        List<Integer> inputList = Arrays.asList(6,1,5,9,0,10,3,2);
        List<Integer> expectedList = Arrays.asList(0,1,2,3,5,6,9,10);
        qs.sort(inputList);
        Assert.assertEquals(expectedList,inputList);
    }
    
    @Test
    public void quicksortPartition() {

        List<Integer> inputList = Arrays.asList(6,1,5,9,0,10,3,2);
        List<Integer> expectedList = Arrays.asList(3, 1, 5, 2, 0, 6, 10, 9);
        qs.partition(inputList,0,7);
        Assert.assertEquals(expectedList,inputList);
    }
    
    @Test
    public void quicksortPartition2() {

        List<Integer> inputList = Arrays.asList(1,3,2);
        List<Integer> expectedList = Arrays.asList(1,2,3);
        int result = qs.partition(inputList,1,2);
        Assert.assertEquals(2,result);
        Assert.assertEquals(expectedList,inputList);
    }
    
}
