package edu.neu.coe.info6205.graphs.gis;

import edu.neu.coe.info6205.graphs.undirected.Edge;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GeoEdgeTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAttribute() {
    }

    @Test
    public void get() {
    }

    @Test
    public void getOther() {
    }

    @Test
    public void equalsTest() {
        GeoPoint london = new MockGeoPoint("London", new Position_Spherical(51.5, 0));
        GeoPoint boston = new MockGeoPoint("Boston", new Position_Spherical(42.3, -71));
        Edge<GeoPoint, String> target1 = new GeoEdge<>(london, boston, "across the pond");
        Edge<GeoPoint, String> target2 = new GeoEdge<>(boston, london, "across the pond");
        assertTrue(target1.equals(target2));
    }


    @Test
    public void hashCodeTest() {
    }

    @Test
    public void toStringTest() {
    }

    @Test
    public void create() {
    }
}