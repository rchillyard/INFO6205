package edu.neu.coe.info6205.sort.elementary;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class InsertionSortMSDTest {

    @Test
    public void sort0() {
        String[] input = "she sells seashells by the seashore the shells she sells are surely seashells".split(" ");
        String[] expected = "are by seashells seashells seashore sells sells she she shells surely the the".split(" ");

        InsertionSortMSD.sort(input, 0, input.length, 0);
        assertArrayEquals(expected, input);
    }

    @Test
    public void sort1() {
        String[] input = "she sells seashells seashore shells she sells surely seashells".split(" ");
        String[] expected = "seashells seashells seashore sells sells she she shells surely".split(" ");

        InsertionSortMSD.sort(input, 0, input.length, 1);
        assertArrayEquals(expected, input);
    }

    @Test
    public void sort1a() {
        String[] input = "she sells seashells by the seashore the shells she sells are surely seashells".split(" ");
        // NOTE that there is some possible randomness in the expected strings, given that we are pretending that all strings have the same first letter.
        String[] expected = "seashells seashells seashore sells sells she the the she shells are surely by".split(" ");

        InsertionSortMSD.sort(input, 0, input.length, 1);
        assertArrayEquals(expected, input);
    }


    @Test
    public void sort2() {
        String[] input = "sells seashells seashore sells seashells".split(" ");
        String[] expected = "seashells seashells seashore sells sells".split(" ");

        InsertionSortMSD.sort(input, 0, input.length, 2);
        assertArrayEquals(expected, input);
    }

}