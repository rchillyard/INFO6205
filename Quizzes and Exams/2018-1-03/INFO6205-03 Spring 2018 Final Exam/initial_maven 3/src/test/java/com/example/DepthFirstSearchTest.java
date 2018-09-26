package com.example;
import static org.junit.Assert.assertArrayEquals;
import java.util.*;
import org.junit.Test;

public class DepthFirstSearchTest {

    public int[] preprocess(int graphSize, int[][] in, int startPoint){
        Graph graph = new Graph(graphSize,in);
        DepthFirstSearch dfs = new DepthFirstSearch(graph,startPoint);
        ArrayList<Integer> yourResult = dfs.getResult();
        int[] array = new int[yourResult.size()];
        for(int i = 0; i < yourResult.size(); i++){
            array[i] = yourResult.get(i);
        }
        return array;
    }

    @Test
    public void test0() {
        int graphSize = 6;
        int[][] in = {{0,1},{1,2},{3,4},{2,5},{4,5}};
        int startPoint = 0;
        int[] correctResult = {0,1,2,5,4,3};

        int[] array = preprocess(graphSize,in,startPoint);

        assertArrayEquals(array, correctResult);
    }

    @Test
    public void test1() {
        int graphSize = 10;
        int[][] in = {{0,1},{1,2},{3,4},{2,5},{4,5},{5,7},{6,7},{4,8},{3,9}};
        int startPoint = 0;
        int[] correctResult = {0,1,2,5,4,3,9,8,7,6};

        int[] array = preprocess(graphSize,in,startPoint);

        assertArrayEquals(array, correctResult);
    }
    @Test
    public void test2() {
        int graphSize = 10;
        int[][] in = {{0,1},{1,2},{3,4},{4,5},{5,7},{6,7},{4,8},{3,9}};
        int startPoint = 0;
        int[] correctResult = {0,1,2};

        int[] array = preprocess(graphSize,in,startPoint);

        assertArrayEquals(array, correctResult);
    }
    @Test
    public void test3() {
        int graphSize = 10;
        int[][] in = {{0,1},{1,2},{3,4},{4,5},{5,7},{6,7},{4,8},{3,9}};
        int startPoint = 5;
        int[] correctResult = {5,4,3,9,8,7,6};

        int[] array = preprocess(graphSize,in,startPoint);

        assertArrayEquals(array, correctResult);
    }

    @Test
    public void test4(){
        int graphSize = 1;
        int[][] in = {};
        int startPoint = 0;
        int[] correctResult = {0};

        int[] array = preprocess(graphSize,in,startPoint);
        assertArrayEquals(array,correctResult);
    }

    @Test
    public void test5(){
        int graphSize = 20;
        int[][] in = {{1,3},{2,4},{3,5},{2,6},{3,6},{4,8},{2,9},{1,11},{2,19},{3,18},{4,17},{5,15},{6,16},{14,15},{13,16},{10,19},{9,0}};
        int startPoint = 0;
        int[] correctResult = {0, 9, 2, 4, 8, 17, 6, 3, 1, 11, 5, 15, 14, 18, 16, 13, 19, 10};

        int[] array = preprocess(graphSize,in,startPoint);
        assertArrayEquals(array,correctResult);
    }

    @Test
    public void test6(){
        int graphSize = 20;
        int[][] in = {{1,3},{2,4},{3,5},{2,6},{3,6},{4,8},{2,9},{1,11},{2,19},{3,18},{4,17},{5,15},{6,16},{14,15},{13,16},{10,19}};
        int startPoint = 5;
        int[] correctResult = {5, 3, 1, 11, 6, 2, 4, 8, 17, 9, 19, 10, 16, 13, 18, 15, 14};

        int[] array = preprocess(graphSize,in,startPoint);
        assertArrayEquals(array,correctResult);
    }

    @Test
    public void test7(){
        int graphSize = 20;
        int[][] in = {{1,3},{2,4},{3,5},{2,6},{3,6},{4,8},{2,9},{1,11},{2,19},{3,18},{4,17},{5,15},{6,16},{14,15},{13,16},{10,19}};
        int startPoint = 19;
        int[] correctResult = {19, 2, 4, 8, 17, 6, 3, 1, 11, 5, 15, 14, 18, 16, 13, 9, 10};

        int[] array = preprocess(graphSize,in,startPoint);
        assertArrayEquals(array,correctResult);
    }

    @Test
    public void test8(){
        int graphSize = 10;
        int[][] in = {{0,1},{1,2},{2,3},{3,4},{4,5},{5,6},{6,7},{7,8},{8,9}};
        int startPoint = 0;
        int[] correctResult = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        int[] array = preprocess(graphSize,in,startPoint);
        assertArrayEquals(array,correctResult);
    }

    @Test
    public void test9(){
        int graphSize = 1;
        int[][] in = {{0,0}};
        int startPoint = 0;
        int[] correctResult = {0};

        int[] array = preprocess(graphSize,in,startPoint);
        assertArrayEquals(array,correctResult);
    }

}
