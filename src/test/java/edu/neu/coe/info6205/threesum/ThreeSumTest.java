package edu.neu.coe.info6205.threesum;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;

public class ThreeSumTest {


    @Test
    public void testGetTriplesJ0() {
        int[] ints = new int[]{-2, 0, 2};
        ThreeSumQuadratic target = new ThreeSumQuadratic(ints);
        List<Triple> triples = target.getTriples(1);
        assertEquals(1, triples.size());
    }

    @Test
    public void testGetTriplesJ1() {
        int[] ints = new int[]{30, -40, -20, -10, 40, 0, 10, 5};
        Arrays.sort(ints);
        ThreeSumQuadratic target = new ThreeSumQuadratic(ints);
        List<Triple> triples = target.getTriples(3);
        assertEquals(2, triples.size());
    }

    @Test
    public void testGetTriplesJ2() {
        Supplier<int[]> intsSupplier = new Source(10, 15, 2L).intsSupplier(10);
        int[] ints = intsSupplier.get();
        ThreeSumQuadratic target = new ThreeSumQuadratic(ints);
        List<Triple> triples = target.getTriples(5);
        assertEquals(1, triples.size());
    }

    @Test
    public void testGetTriples0() {
        int[] ints = new int[]{30, -40, -20, -10, 40, 0, 10, 5};
        Arrays.sort(ints);
        System.out.println("ints: " + Arrays.toString(ints));
        ThreeSumQuadratic target = new ThreeSumQuadratic(ints);
        Triple[] triples = target.getTriples();
        System.out.println("triples: " + Arrays.toString(triples));
        assertEquals(4, triples.length);
        assertEquals(4, new ThreeSumQuadrithmic(ints).getTriples().length);
        assertEquals(4, new ThreeSumCubic(ints).getTriples().length);
    }

    @Test
    public void testGetTriples1() {
        Supplier<int[]> intsSupplier = new Source(20, 20, 1L).intsSupplier(10);
        int[] ints = intsSupplier.get();
//        System.out.println("ints: "+Arrays.toString(ints));
//        System.out.println("length of ints: "+ints.length);
        ThreeSumQuadratic target = new ThreeSumQuadratic(ints);
        Triple[] triples = target.getTriples();
//        assertEquals(4, triples.length);
        System.out.println(Arrays.toString(triples));
        Triple[] triples1 = new ThreeSumQuadrithmic(ints).getTriples();
        System.out.println(Arrays.toString(triples1));
//        assertEquals(4, triples1.length);
        Triple[] triples2 = new ThreeSumCubic(ints).getTriples();
        System.out.println(Arrays.toString(triples2));
//        assertEquals(4, triples2.length);
    }

    @Test
    public void testGetTriples2() {
        Supplier<int[]> intsSupplier = new Source(10, 15, 3L).intsSupplier(10);
        int[] ints = intsSupplier.get();
        ThreeSumQuadratic target = new ThreeSumQuadratic(ints);
        System.out.println(Arrays.toString(ints));
        Triple[] triples = target.getTriples();
        System.out.println(Arrays.toString(triples));
        assertEquals(1, triples.length);
        assertEquals(1, new ThreeSumQuadrithmic(ints).getTriples().length);
        assertEquals(1, new ThreeSumCubic(ints).getTriples().length);
    }

    @Ignore // Slow
    public void testGetTriples3() {
        Supplier<int[]> intsSupplier = new Source(1000, 1000).intsSupplier(10);
        int[] ints = intsSupplier.get();
        ThreeSumQuadratic target = new ThreeSumQuadratic(ints);
        Triple[] triplesQuadratic = target.getTriples();
        Triple[] triplesCubic = new ThreeSumCubic(ints).getTriples();
        int expected1 = triplesCubic.length;
        Triple[] triplesQuadrithmic = new ThreeSumQuadrithmic(ints).getTriples();
        int expected2 = triplesQuadrithmic.length;
        assertEquals(expected1, expected2);
        assertEquals(expected1, triplesQuadratic.length);
    }

    @Ignore // Slow
    public void testGetTriples4() {
        Supplier<int[]> intsSupplier = new Source(1500, 1000).intsSupplier(10);
        int[] ints = intsSupplier.get();
        ThreeSumQuadratic target = new ThreeSumQuadratic(ints);
        Triple[] triplesQuadratic = target.getTriples();
        Triple[] triplesCubic = new ThreeSumCubic(ints).getTriples();
        int expected1 = triplesCubic.length;
        Triple[] triplesQuadrithmic = new ThreeSumQuadrithmic(ints).getTriples();
        int expected2 = triplesQuadrithmic.length;
        assertEquals(expected1, expected2);
        assertEquals(expected1, triplesQuadratic.length);
    }

    @Test
    public void testGetTriplesC0() {
        int[] ints = new int[]{30, -40, -20, -10, 40, 0, 10, 5};
        Arrays.sort(ints);
        System.out.println("ints: " + Arrays.toString(ints));
        ThreeSumQuadratic target = new ThreeSumQuadratic(ints);
        Triple[] triples = target.getTriples();
        System.out.println("triples: " + Arrays.toString(triples));
        assertEquals(4, triples.length);
        assertEquals(4, new ThreeSumQuadrithmic(ints).getTriples().length);
        assertEquals(4, new ThreeSumCubic(ints).getTriples().length);
    }

    @Test
    public void testGetTriplesC1() {
        Supplier<int[]> intsSupplier = new Source(20, 20, 1L).intsSupplier(10);
        int[] ints = intsSupplier.get();
//        System.out.println("ints: "+Arrays.toString(ints));
//        System.out.println("length of ints: "+ints.length);
        ThreeSum target = new ThreeSumQuadraticWithCalipers(ints);
        Triple[] triples = target.getTriples();
//        assertEquals(4, triples.length);
        System.out.println(Arrays.toString(triples));
        Triple[] triples1 = new ThreeSumQuadrithmic(ints).getTriples();
        System.out.println(Arrays.toString(triples1));
//        assertEquals(4, triples1.length);
        Triple[] triples2 = new ThreeSumCubic(ints).getTriples();
        System.out.println(Arrays.toString(triples2));
//        assertEquals(4, triples2.length);
    }

    @Test
    public void testGetTriplesC2() {
        Supplier<int[]> intsSupplier = new Source(10, 15, 3L).intsSupplier(10);
        int[] ints = intsSupplier.get();
        ThreeSum target = new ThreeSumQuadraticWithCalipers(ints);
        System.out.println(Arrays.toString(ints));
        Triple[] triples = target.getTriples();
        System.out.println(Arrays.toString(triples));
        assertEquals(1, triples.length);
        assertEquals(1, new ThreeSumQuadrithmic(ints).getTriples().length);
        assertEquals(1, new ThreeSumCubic(ints).getTriples().length);
    }

    @Test
    public void testGetTriplesC3() {
        Supplier<int[]> intsSupplier = new Source(1000, 1000).intsSupplier(10);
        int[] ints = intsSupplier.get();
//        System.out.println("ints: "+Arrays.toString(ints));
        ThreeSum target = new ThreeSumQuadraticWithCalipers(ints);
        Triple[] triplesQuadratic = target.getTriples();
//        System.out.println("triplesQuadratic:   "+Arrays.toString(triplesQuadratic));
        Triple[] triplesCubic = new ThreeSumCubic(ints).getTriples();
//        System.out.println("triplesCubic:       "+Arrays.toString(triplesCubic));
        int expected1 = triplesCubic.length;
        Triple[] triplesQuadrithmic = new ThreeSumQuadrithmic(ints).getTriples();
//        System.out.println("triplesQuadrithmic: "+Arrays.toString(triplesQuadrithmic));
        int expected2 = triplesQuadrithmic.length;
        assertEquals(expected1, expected2);
        assertEquals(expected1, triplesQuadratic.length);
    }

    @Ignore
    public void testGetTriplesC4() {
        Supplier<int[]> intsSupplier = new Source(1500, 1000).intsSupplier(10);
        int[] ints = intsSupplier.get();
//        System.out.println("ints: "+Arrays.toString(ints));
        ThreeSum target = new ThreeSumQuadraticWithCalipers(ints);
        Triple[] triplesQuadratic = target.getTriples();
//        System.out.println("triplesQuadratic:   "+Arrays.toString(triplesQuadratic));
        Triple[] triplesCubic = new ThreeSumCubic(ints).getTriples();
//        System.out.println("triplesCubic:       "+Arrays.toString(triplesCubic));
        int expected1 = triplesCubic.length;
        Triple[] triplesQuadrithmic = new ThreeSumQuadrithmic(ints).getTriples();
//        System.out.println("triplesQuadrithmic: "+Arrays.toString(triplesQuadrithmic));
        int expected2 = triplesQuadrithmic.length;
        assertEquals(expected1, expected2);
        assertEquals(expected1, triplesQuadratic.length);
    }

}