package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void test0(){
        Solution solution = new Solution();
        int[][] test = {{1,2,3,4},{2,3,4,5}};
        int[] result = solution.solution(test);
        int[] answer = {1,2,2,3,3,4,4,5};
        assertArrayEquals(result,answer);
    }
    @Test
    public void test1(){
        Solution solution = new Solution();
        int[][] test = {{1,2,3,4},{1,3},{0,2}};
        int[] result = solution.solution(test);
        int[] answer = {0,1,1,2,2,3,3,4};
        assertArrayEquals(result,answer);
    }
    @Test
    public void test2(){
        Solution solution = new Solution();
        int[][] test = {{1,2,3,4},{2,3,4,5},{},{1,3},{0,2}};
        int[] result = solution.solution(test);
        int[] answer = {0,1,1,2,2,2,3,3,3,4,4,5};
        assertArrayEquals(result,answer);
    }
    @Test
    public void test3(){
        Solution solution = new Solution();
        int[][] test = {{1,2,3,4},{},{},{1,3},{0,2}};
        int[] result = solution.solution(test);
        int[] answer = {0,1,1,2,2,3,3,4};
        assertArrayEquals(result,answer);
    }
    @Test
    public void test4(){
        Solution solution = new Solution();
        int[][] test = {{1,1,1,1,1},{1,1,1},{},{1},{1,1,1,1,1,1}};
        int[] result = solution.solution(test);
        int[] answer = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        assertArrayEquals(result,answer);
    }
    @Test
    public void test5(){
        Solution solution = new Solution();
        int[][] test = {{},{},{},{},{}};
        int[] result = solution.solution(test);
        int[] answer = {};
        assertArrayEquals(result,answer);
    }
    @Test
    public void test6(){
        Solution solution = new Solution();
        int[][] test = {};
        int[] result = solution.solution(test);
        int[] answer = {};
        assertArrayEquals(result,answer);
    }
}
