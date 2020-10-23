package edu.neu.coe.info6205.questions;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ResultTest {

    @Test
    public void testCountDuplicates() {
        assertEquals(2, Result.countDuplicates(Arrays.asList(1, 1, 2, 2, 2, 3)));
        assertEquals(2, Result.countDuplicates(Arrays.asList(1, 3, 1, 4, 5, 6, 3, 2)));
        assertEquals(4, Result.countDuplicates(Arrays.asList(3, 4, 1, 2, 5, 4, 3, 2, 1)));
    }
}