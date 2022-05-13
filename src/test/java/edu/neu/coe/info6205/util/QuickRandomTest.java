package edu.neu.coe.info6205.util;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QuickRandomTest {

    @Test
    public void testGet0() {
        QuickRandom random = new QuickRandom(0xAAAAAAAAL);
        assertEquals(0x2BFF5, random.get());
        assertEquals(0x287AB56B, random.get());
        assertEquals(0x242AAA00, random.get());
        assertEquals(0x5C38C415, random.get());
    }

    @Test
    public void testGet1() {
        QuickRandom random = new QuickRandom(1000, 0xAAAAAAAAL);
        assertEquals(213, random.get());
        assertEquals(475, random.get());
        assertEquals(808, random.get());
        assertEquals(85, random.get());
        assertEquals(320, random.get());
    }

    @Test
    public void testGet2() {
        QuickRandom random = new QuickRandom(1000, 0xAAAAAAAAL);
        assertEquals(713, random.get(500));
        assertEquals(975, random.get(500));
        assertEquals(808, random.get(500));
        assertEquals(585, random.get(500));
        assertEquals(820, random.get(500));
    }


    @Test
    public void testGet3() {
        int n = 1000;
        QuickRandom random = new QuickRandom(n);
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < 100 * n; i++) {
            int x = random.get();
            Integer y = freq.getOrDefault(x, 0);
            freq.put(x, y + 1);
        }
        for (int i = 0; i < n; i++) {
            assertTrue(freq.getOrDefault(i, 0) > 0);
        }
    }
}