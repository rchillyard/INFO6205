package edu.neu.coe.info6205.util;

import org.junit.Test;

import static edu.neu.coe.info6205.util.GeoConversions.Deg2UTM;
import static edu.neu.coe.info6205.util.GeoConversions.Degrees;
import static org.junit.Assert.assertEquals;

public class GeoConversionsTest {

    @Test
    public void testConvertDegToUTM_1() {
        double lat = new Degrees(13, 24, 45).decimal;
        double lon = new Degrees(103, 52, 0).decimal;
        Deg2UTM utm = new Deg2UTM(lat, lon);
        assertEquals(377299, utm.Easting);
        assertEquals(1483035, utm.Northing);
        assertEquals(48, utm.Zone);
        assertEquals('N', utm.Letter);
    }

    @Test
    public void testConvertDegToUTM_2() {
        Deg2UTM utm = new Deg2UTM(51.253819, 1.0086);
        assertEquals(361034, utm.Easting);
        assertEquals(5679935, utm.Northing);
        assertEquals(31, utm.Zone);
        assertEquals('N', utm.Letter);

    }
}