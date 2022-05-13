package edu.neu.coe.info6205.threesum;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;

public class TwoSumTest {


    @Test
    public void testGetPairsJ0() {
        int[] ints = new int[]{-2, 0, 2};
        TwoSumQuadratic target = new TwoSumQuadratic(ints);
        Pair[] triples = target.getPairs();
        assertEquals(1, triples.length);
    }

    @Test
    public void testGetPairsJ1() {
        int[] ints = new int[]{30, -40, -20, -10, 40, 0, 10, 5};
        Arrays.sort(ints);
        TwoSumQuadratic target = new TwoSumQuadratic(ints);
        Pair[] triples = target.getPairs();
        assertEquals(2, triples.length);
    }

//    @Test
//    public void testGetPairsJ2() {
//        Supplier<int[]> intsSupplier = new Source(10, 15, 2L).intsSupplier(10);
//        int[] ints = intsSupplier.get();
//        System.out.println(ints);
//        TwoSumQuadratic target = new TwoSumQuadratic(ints);
//        Pair[] triples = target.getPairs();
//        assertEquals(1, triples.length);
//    }

//    @Test
//    public void testGetPairs0() {
//        int[] ints = new int[]{30, -40, -20, -10, 40, 0, 10, 5};
//        Arrays.sort(ints);
//        System.out.println("ints: " + Arrays.toString(ints));
//        TwoSumQuadratic target = new TwoSumQuadratic(ints);
//        Pair[] triples = target.getPairs();
//        System.out.println("triples: " + Arrays.toString(triples));
//        assertEquals(2, triples.length);
//        assertEquals(4, new TwoSumQuadratic(ints).getPairs().length);
//    }

    @Test
    public void testGetPairs1() {
        Supplier<int[]> intsSupplier = new Source(20, 20, 1L).intsSupplier(10);
        int[] ints = intsSupplier.get();
//        System.out.println("ints: "+Arrays.toString(ints));
//        System.out.println("length of ints: "+ints.length);
        TwoSumQuadratic target = new TwoSumQuadratic(ints);
        Pair[] triples = target.getPairs();
//        assertEquals(4, triples.length);
        System.out.println(Arrays.toString(triples));
        Pair[] triples2 = new TwoSumQuadratic(ints).getPairs();
        System.out.println(Arrays.toString(triples2));
//        assertEquals(4, triples2.length);
    }

//    @Test
//    public void testGetPairs2() {
//        Supplier<int[]> intsSupplier = new Source(10, 15, 3L).intsSupplier(10);
//        int[] ints = intsSupplier.get();
//        TwoSumQuadratic target = new TwoSumQuadratic(ints);
//        System.out.println(Arrays.toString(ints));
//        Pair[] triples = target.getPairs();
//        System.out.println(Arrays.toString(triples));
//        assertEquals(1, triples.length);
//        assertEquals(1, new TwoSumQuadratic(ints).getPairs().length);
//    }

    @Test // Slow
    public void testGetPairs3() {
        Supplier<int[]> intsSupplier = new Source(1000, 1000).intsSupplier(10);
        int[] ints = intsSupplier.get();
        TwoSumQuadratic target = new TwoSumQuadratic(ints);
        Pair[] triplesQuadratic = target.getPairs();
        Pair[] triplesCubic = new TwoSumQuadratic(ints).getPairs();
        int expected1 = triplesCubic.length;
        assertEquals(expected1, triplesQuadratic.length);
    }

    @Test // Slow
    public void testGetPairs4() {
        Supplier<int[]> intsSupplier = new Source(1500, 1000).intsSupplier(10);
        int[] ints = intsSupplier.get();
        TwoSumQuadratic target = new TwoSumQuadratic(ints);
        Pair[] triplesQuadratic = target.getPairs();
        Pair[] triplesCubic = new TwoSumQuadratic(ints).getPairs();
        int expected1 = triplesCubic.length;
        Pair[] triplesQuadrithmic = new TwoSumWithCalipers(ints).getPairs();
        int expected2 = triplesQuadrithmic.length;
        System.out.println(expected2);
        assertEquals(expected1, expected2);
        assertEquals(expected1, triplesQuadratic.length);
    }

    @Test
    public void testGetPairsC0() {
        int[] ints = new int[]{30, -40, -20, -10, 40, 0, 10, 5};
        Arrays.sort(ints);
        System.out.println("ints: " + Arrays.toString(ints));
        TwoSumQuadratic target = new TwoSumQuadratic(ints);
        Pair[] triples = target.getPairs();
        System.out.println("triples: " + Arrays.toString(triples));
        assertEquals(2, triples.length);
        assertEquals(2, new TwoSumWithCalipers(ints).getPairs().length);
    }

    @Test
    public void testGetPairsC1() {
        Supplier<int[]> intsSupplier = new Source(20, 20, 1L).intsSupplier(10);
        int[] ints = intsSupplier.get();
//        System.out.println("ints: "+Arrays.toString(ints));
//        System.out.println("length of ints: "+ints.length);
        TwoSum target = new TwoSumWithCalipers(ints);
        Pair[] triples = target.getPairs();
//        assertEquals(4, triples.length);
        System.out.println(Arrays.toString(triples));
        Pair[] triples1 = new TwoSumQuadratic(ints).getPairs();
        System.out.println(Arrays.toString(triples1));
//        assertEquals(4, triples1.length);
    }

//    @Test
//    public void testGetPairsC2() {
//        Supplier<int[]> intsSupplier = new Source(10, 15, 3L).intsSupplier(10);
//        int[] ints = intsSupplier.get();
//        TwoSum target = new TwoSumQuadraticWithCalipers(ints);
//        System.out.println(Arrays.toString(ints));
//        Pair[] triples = target.getPairs();
//        System.out.println(Arrays.toString(triples));
//        assertEquals(1, triples.length);
//        assertEquals(1, new TwoSumQuadrithmic(ints).getPairs().length);
//        assertEquals(1, new TwoSumCubic(ints).getPairs().length);
//    }

//    @Test
//    public void testGetPairsC3() {
//        Supplier<int[]> intsSupplier = new Source(1000, 1000).intsSupplier(10);
//        int[] ints = intsSupplier.get();
////        System.out.println("ints: "+Arrays.toString(ints));
//        TwoSum target = new TwoSumQuadraticWithCalipers(ints);
//        Pair[] triplesQuadratic = target.getPairs();
////        System.out.println("triplesQuadratic:   "+Arrays.toString(triplesQuadratic));
//        Pair[] triplesCubic = new TwoSumCubic(ints).getPairs();
////        System.out.println("triplesCubic:       "+Arrays.toString(triplesCubic));
//        int expected1 = triplesCubic.length;
//        Pair[] triplesQuadrithmic = new TwoSumQuadrithmic(ints).getPairs();
////        System.out.println("triplesQuadrithmic: "+Arrays.toString(triplesQuadrithmic));
//        int expected2 = triplesQuadrithmic.length;
//        assertEquals(expected1, expected2);
//        assertEquals(expected1, triplesQuadratic.length);
//    }

//    @Test
//    public void testGetPairsC4() {
//        Supplier<int[]> intsSupplier = new Source(1500, 1000).intsSupplier(10);
//        int[] ints = intsSupplier.get();
////        System.out.println("ints: "+Arrays.toString(ints));
//        TwoSum target = new TwoSumQuadraticWithCalipers(ints);
//        Pair[] triplesQuadratic = target.getPairs();
////        System.out.println("triplesQuadratic:   "+Arrays.toString(triplesQuadratic));
//        Pair[] triplesCubic = new TwoSumCubic(ints).getPairs();
////        System.out.println("triplesCubic:       "+Arrays.toString(triplesCubic));
//        int expected1 = triplesCubic.length;
//        Pair[] triplesQuadrithmic = new TwoSumQuadrithmic(ints).getPairs();
////        System.out.println("triplesQuadrithmic: "+Arrays.toString(triplesQuadrithmic));
//        int expected2 = triplesQuadrithmic.length;
//        assertEquals(expected1, expected2);
//        assertEquals(expected1, triplesQuadratic.length);
//    }

}