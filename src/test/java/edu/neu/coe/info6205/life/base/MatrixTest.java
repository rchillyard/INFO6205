package edu.neu.coe.info6205.life.base;

import edu.neu.coe.info6205.util.PrivateMethodTester;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MatrixTest {

    @Test
    public void testConstructor0() {
        final Matrix target = new Matrix(3, 3);
        assertEquals(0, target.getCount());
        final PrivateMethodTester targetTester = new PrivateMethodTester(target);
        assertEquals(3, targetTester.invokePrivate("getWidth"));
        assertEquals(3, targetTester.invokePrivate("getHeight"));
        final Object[] objects = new Object[3];
        Matrix.Bits[][] rowsExpected = Arrays.copyOf(objects, objects.length, Matrix.Bits[][].class);
        Matrix.Bits[] row = Arrays.copyOf(objects, 1, Matrix.Bits[].class);
        for (int i = 0; i < 1; i++) row[i] = new Matrix.Bits(3);
        for (int i = 0; i < 3; i++) rowsExpected[i] = row;
        final Matrix.Bits[][] rowsActual = (Matrix.Bits[][]) targetTester.invokePrivate("copyCells");
        for (int i = 0; i < 3; i++) assertArrayEquals(rowsExpected[i], rowsActual[i]);
    }

    @Test
    public void testConstructor1() {
        final Matrix matrix1 = new Matrix(3, 3);
        Matrix target = new Matrix(matrix1, 0, 0, 0, 0);
        assertEquals(matrix1, target);
    }

    @Test
    public void testConstructor2() {
        final Matrix matrix1 = new Matrix(3, 3);
        Matrix target = new Matrix(matrix1, 1, 1, 1, 1);
        final PrivateMethodTester targetTester = new PrivateMethodTester(target);
        assertEquals(5, targetTester.invokePrivate("getWidth"));
        assertEquals(5, targetTester.invokePrivate("getHeight"));
        final Object[] objects = new Object[5];
        Matrix.Bits[][] rowsExpected = Arrays.copyOf(objects, objects.length, Matrix.Bits[][].class);
        Matrix.Bits[] row = Arrays.copyOf(objects, 1, Matrix.Bits[].class);
        for (int i = 0; i < 1; i++) row[i] = new Matrix.Bits(5);
        for (int i = 0; i < 5; i++) rowsExpected[i] = row;
        final Matrix.Bits[][] rowsActual = (Matrix.Bits[][]) targetTester.invokePrivate("copyCells");
        for (int i = 0; i < 5; i++) assertArrayEquals(rowsExpected[i], rowsActual[i]);

    }

    //		@Test
    public void testConstructor3() {
        final Matrix matrix3 = new Matrix(3, 3, (x, y) -> x * (y / 2), (x, y) -> y % 2 == 0 ? 0L : 0xFFFFFFFFL);
        Matrix target = new Matrix(matrix3, 1, 1, 1, 1);
        final PrivateMethodTester targetTester = new PrivateMethodTester(target);
        assertEquals(5, targetTester.invokePrivate("getWidth"));
        assertEquals(5, targetTester.invokePrivate("getHeight"));
        final Object[] objects = new Object[5];
        Matrix.Bits[][] rowsExpected = Arrays.copyOf(objects, objects.length, Matrix.Bits[][].class);
        Matrix.Bits[] row = Arrays.copyOf(objects, 1, Matrix.Bits[].class);
        Matrix.Bits[] rowEmpty = Arrays.copyOf(objects, 1, Matrix.Bits[].class);
        for (int i = 0; i < 1; i++) row[i] = new Matrix.Bits(5);
        for (int i = 0; i < 1; i++) rowEmpty[i] = new Matrix.Bits(0);
        rowsExpected[0] = rowEmpty;
        for (int i = 1; i < 4; i++) rowsExpected[i] = row;
        rowsExpected[4] = rowEmpty;
        final Matrix.Bits[][] rowsActual = (Matrix.Bits[][]) targetTester.invokePrivate("copyCells");
        for (int i = 0; i < 5; i++) assertArrayEquals(rowsExpected[i], rowsActual[i]);
    }

    @Test
    public void testConstructor4() {
        // TODO introduce an assertion
        final Matrix matrix2 = new Matrix(3, 3, (x, y) -> x * y, (x, y) -> 0xFFFFFFFFL);
    }


    @Test
    public void testRender() {
        Matrix target = new Matrix(10, 1);
        assertEquals(" −−−−−−−−−−\n" +
                "|..........|\n" +
                " −−−−−−−−−−\n", target.render());
        target.addCell(new Point(1, 0));
        assertEquals(" −−−−−−−−−−\n" +
                "|.*........|\n" +
                " −−−−−−−−−−\n", target.render());
    }

    @Test
    public void testToString() {
        Matrix target = new Matrix(10, 1);
        assertEquals("0\n", target.toString());
        target.addCell(new Point(1, 0));
        assertEquals("40000000\n", target.toString());
    }

    @Test
    public void testIsCell1() {
        Matrix target = new Matrix(3, 3);
        for (int k = 0; k < 3; k++)
            for (int l = 0; l < 3; l++)
                assertFalse(target.isCell(new Point(k, l)));
    }

    @Test
    public void testIsCell2() {
        Matrix target = new Matrix(3, 3, (x, y) -> x * y, (x, y) -> 0xFFFFFFFFL);
        for (int k = 0; k < 3; k++)
            for (int l = 0; l < 3; l++)
                assertTrue(target.isCell(new Point(k, l)));
    }

    @Test
    public void testIsCell3() {
        Matrix target = new Matrix(3, 3, (x, y) -> x * (y / 2), (x, y) -> y % 2 == 0 ? 0L : 0xFFFFFFFFL);
        for (int k = 0; k < 3; k++)
            for (int l = 0; l < 3; l++)
                assertEquals(l % 2 != 0, target.isCell(new Point(k, l)));
    }

    @Test
    public void testAddCell() {
        Matrix target = new Matrix(3, 3);
        Point point = new Point(1, 1);
        target.addCell(point);
        assertTrue(target.isCell(point));
    }

    @Test
    public void testRemoveCell() {
        Matrix target = new Matrix(3, 3);
        Point point = new Point(1, 1);
        target.addCell(point);
        target.removeCell(point);
        assertFalse(target.isCell(point));
    }

    @Test
    public void testCountNeighbors0() {
        Matrix target = new Matrix(3, 3);
        final Matrix.Neighbors neighbors = target.getNeighbors();
        for (int k = 0; k < 3; k++)
            for (int l = 0; l < 3; l++)
                assertEquals(0, neighbors.getCount(new Point(k, l)));
    }

    @Test
    public void testCountNeighbors1() {
        Matrix target = new Matrix(3, 3);
        Point point = new Point(1, 1);
        target.addCell(point);
        final Matrix.Neighbors neighbors = target.getNeighbors();
        for (int k = 0; k < 3; k++)
            for (int l = 0; l < 3; l++)
                assertEquals(k == 1 && l == 1 ? 0 : 1, neighbors.getCount(new Point(k, l)));
    }

    @Test
    public void testCountNeighbors2() {
        Matrix target = new Matrix(4, 4);
        target.addCell(new Point(1, 1));
        target.addCell(new Point(2, 2));
//				System.out.println("matrix: \n" + target.render());
        final Matrix.Neighbors neighbors = target.getNeighbors();
//				System.out.println(target.getNeighbors());
        for (int k = 0; k < 4; k++)
            for (int l = 0; l < 4; l++) {
                final int expected = (k == 3 && l == 0 || k == 0 && l == 3) ? 0 : (k == 2 && l == 1 || k == 1 && l == 2) ? 2 : 1;
                assertEquals("count for " + k + ", " + l,
                        expected, neighbors.getCount(new Point(k, l)));
            }
    }

    @Test
    public void testCountNeighbors3() {
        Matrix target = new Matrix(5, 5);
        target.addCell(new Point(1, 1));
        target.addCell(new Point(2, 2));
        target.addCell(new Point(2, 3));
//				System.out.println("matrix: \n" + target.render());
        final Matrix.Neighbors neighbors = target.getNeighbors();
        assertTrue(neighbors.doCountsMatch());
//				System.out.println(neighbors.toString());
    }

    @Test
    public void testGetCount() {
        assertEquals(0, new Matrix(3, 3).getCount());
        assertEquals(9, new Matrix(3, 3, (x, y) -> x * y, (x, y) -> 0xFFFFFFFFL).getCount());
        assertEquals(3, new Matrix(3, 3, (x, y) -> x * (y / 2), (x, y) -> y % 2 == 0 ? 0L : 0xFFFFFFFFL).getCount());
    }

}