package edu.neu.coe.info6205.lab_1;

import edu.neu.coe.info6205.hashtable.FrequencyCounter;
import edu.neu.coe.info6205.util.PrivateMethodTester;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WheelOfFortuneTest {

    WheelOfFortune<String> wheel; // This wheel is not random: seed is always 0L
    private static WheelOfFortune.Event<String> heads = WheelOfFortune.valueOf("Heads", 1);
    private static WheelOfFortune.Event<String> tails = WheelOfFortune.valueOf("Tails", 1);


    @Before
    public void setUp() throws Exception {
        wheel = new WheelOfFortune<>(0L, heads, tails);
    }

    @After
    public void tearDown() throws Exception {
        wheel = null;
    }

    @Test
    public void getTotal() {
        PrivateMethodTester tester = new PrivateMethodTester(wheel);
        assertEquals("total", 2, (int) (Integer)tester.invokePrivate("getTotal"));
    }

    @Test
    public void get() {
        assertEquals(tails.event, wheel.get());
        assertEquals(tails.event, wheel.get());
        assertEquals(heads.event, wheel.get());
        assertEquals(tails.event, wheel.get());
        assertEquals(tails.event, wheel.get());
        assertEquals(heads.event, wheel.get());
        assertEquals(tails.event, wheel.get());
        assertEquals(heads.event, wheel.get());
        assertEquals(tails.event, wheel.get());
        assertEquals(tails.event, wheel.get());
        assertEquals(heads.event, wheel.get());
        assertEquals(heads.event, wheel.get());
    }

    @Test
    public void getRandom0() {
        FrequencyCounter<String> frequencyCounter = new FrequencyCounter<>();
        for (int i = 0; i < 10000; i++) frequencyCounter.increment(wheel.get());
        assertEquals(5000, frequencyCounter.get("Heads"), 500);
        assertEquals(5000, frequencyCounter.get("Tails"), 500);
    }

    @Test
    public void getRandom1() {
        WheelOfFortune.Event<String> one = WheelOfFortune.valueOf("1", 1);
        WheelOfFortune.Event<String> two = WheelOfFortune.valueOf("2", 2);
        WheelOfFortune.Event<String> three = WheelOfFortune.valueOf("3", 3);
        WheelOfFortune.Event<String> four = WheelOfFortune.valueOf("4", 4);
        WheelOfFortune<String> wheel1 = new WheelOfFortune<>(one, two, three, four);
        FrequencyCounter<String> frequencyCounter = new FrequencyCounter<>();
        for (int i = 0; i < 10000; i++) frequencyCounter.increment(wheel1.get());
        assertEquals(1000, frequencyCounter.get("1"), 100);
        assertEquals(2000, frequencyCounter.get("2"), 200);
        assertEquals(3000, frequencyCounter.get("3"), 300);
        assertEquals(4000, frequencyCounter.get("4"), 400);
    }

    @Test
    public void valueOf() {
        WheelOfFortune.Event<String> event = WheelOfFortune.valueOf("event", 99);
        assertEquals(new WheelOfFortune.Event("event", 99), event);
    }
}