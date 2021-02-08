package edu.neu.coe.info6205.threesum;

import org.junit.Test;

import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;

public class SourceTest {

    @Test
    public void intsSupplier() {
        int n = 20;
        Source source = new Source(n, 10, 0L);
        Supplier<int[]> supplier = source.intsSupplier(10);
        int[] ints = supplier.get();
        assertEquals(n, ints.length);
    }
}