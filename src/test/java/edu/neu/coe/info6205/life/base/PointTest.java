package edu.neu.coe.info6205.life.base;

import edu.neu.coe.info6205.util.PrivateMethodTester;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PointTest {

    final static Point Origin = new Point(0, 0);

    @Test
    public void getXTest() {
        // TESTME
    }

    @Test
    public void getYTest() {
        // TESTME
    }

    @Test
    public void moveTest() {
        // TESTME
    }

    @Test
    public void testMoveTest() {
        Point p01 = new Point(0, 1);
        Point p10 = new Point(1, 0);
        Point p11 = new Point(1, 1);

        assertEquals(p01, Origin.move(0, 1));
        assertEquals(p10, Origin.move(1, 0));
        assertEquals(p11, Origin.move(1, 1));
        assertEquals(Origin, Origin.move(0, 0));
    }

    @Test
    public void validTest() {
        // TESTME
    }

    @Test
    public void compareTest() {
        Point p00 = Origin;
        Point p01 = new Point(0, 1);
        Point p10 = new Point(1, 0);
        Point p11 = new Point(1, 1);
        assertEquals(0, p00.compare(p00));
        assertEquals(3, p00.compare(p01));
        assertEquals(1, p00.compare(p10));
        assertEquals(4, p00.compare(p11));
        assertEquals(-3, p01.compare(p00));
        assertEquals(0, p01.compare(p01));
        assertEquals(-2, p01.compare(p10));
        assertEquals(1, p01.compare(p11));
        assertEquals(-1, p10.compare(p00));
        assertEquals(2, p10.compare(p01));
        assertEquals(-0, p10.compare(p10));
        assertEquals(3, p10.compare(p11));
        assertEquals(-4, p11.compare(p00));
        assertEquals(-1, p11.compare(p01));
        assertEquals(-3, p11.compare(p10));
        assertEquals(0, p11.compare(p11));
    }

    @Test
    public void testEquals() {
        Point p00 = Origin;
        Point p01 = new Point(0, 1);
        assertEquals(p00, p00);
        assertEquals(p01, p01);
        assertNotEquals(p00, p01);
        assertNotEquals(p01, p00);
    }

    @Test
    public void testHashCode() {
        // TESTME
    }

    @Test
    public void testToString() {
        // TESTME
    }

    @Test
    public void testRelative() {
        Point origin = new Point(1, 1);
        assertEquals(Origin, origin.relative(origin));
    }

    @Test
    public void testCompareTo() {
        Point p00 = Origin;
        Point p01 = new Point(0, 1);
        assertEquals(-1, p00.compareTo(p01));
    }

    @Test
    public void testDistance() {
        assertEquals(0.0, new PrivateMethodTester(Origin).invokePrivate("distance"));
        assertEquals(1.0, new PrivateMethodTester(new Point(0, 1)).invokePrivate("distance"));
        assertEquals(Math.sqrt(2), (Double) new PrivateMethodTester(new Point(1, 1)).invokePrivate("distance"), 0.00001);
    }
}