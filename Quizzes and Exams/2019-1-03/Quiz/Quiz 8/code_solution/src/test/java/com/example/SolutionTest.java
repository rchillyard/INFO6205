package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void sink5thElementOf10() {
        Comparable[] A = { 12, 9, 7, -4, 0, 2, 8, 7, 6, 44 };
        Solution.sink(A, 5, 10);
        assertEquals(0, A[9]);
        assertEquals(44, A[4]);
    }

    @Test
    public void sink3rdElementOf6() {
        Comparable[] A = { 0, 1, 2, 3, 4, 5 };
        Solution.sink(A, 3, 6);
        assertEquals(5, A[2]);
        assertEquals(2, A[5]);
    }

    @Test
    public void sink4thElementOf3() {
        Comparable[] A = { 6, 5, 4, 3, 2, 1, 0, -1 };
        Solution.sink(A, 4, 3);
        assertEquals(-1, A[7]);
        assertEquals(2, A[4]);

    }

    @Test
    public void sortEmptyArray() {
        Comparable[] A = {};
        Solution.sort(A);
        assertEquals(0, A.length);

    }

    @Test
    public void sortSingleElementArray() {
        Comparable[] A = { "a" };
        Solution.sort(A);
        assertEquals("a", A[0]);

    }

    @Test
    public void sortIntegers() {
        Comparable[] A = { 2, -1, 0, 3 };
        Solution.sort(A);
        assertEquals(-1, A[0]);
        assertEquals(0, A[1]);
        assertEquals(2, A[2]);
        assertEquals(3, A[3]);
    }

    @Test
    public void sortStrings() {
        Comparable[] A = { "c", "a", "b", "c" };
        Solution.sort(A);
        assertEquals("a", A[0]);
        assertEquals("b", A[1]);
        assertEquals("c", A[2]);
        assertEquals("c", A[3]);
    }

}
