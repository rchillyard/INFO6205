package edu.neu.coe.info6205.graphs.tunnels;

import edu.neu.coe.info6205.graphs.gis.Position_Spherical;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BuildingTest {

    private Building sn;
    private String snell;

    @Before
    public void setUp() throws Exception {
        snell = "Snell Engineering Center";
        sn = new Building(54, "SN", "Center", 42.3382885, -71.0910495, true, snell);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getName() {
        assertEquals(snell, sn.getName());
    }

    @Test
    public void getPosition() {
        assertEquals(new Position_Spherical(42.3382885, -71.0910495), sn.getPosition());
    }

    @Test
    public void toStringTest() {
        assertEquals(snell, sn.toString());
    }

}