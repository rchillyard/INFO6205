package edu.neu.coe.info6205.lab_1;

import edu.neu.coe.info6205.symbolTable.hashtable.FrequencyCounter;
import edu.neu.coe.info6205.util.PrivateMethodTester;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WheelOfFortuneTest {

    WheelOfFortune<String> wheel; // This wheel is not random: seed is always 0L
    private static final WheelOfFortune.Event<String> heads = WheelOfFortune.valueOf("Heads", 1);
    private static final WheelOfFortune.Event<String> tails = WheelOfFortune.valueOf("Tails", 1);


    @Before
    public void setUp() {
        wheel = new WheelOfFortune<>(0L, heads, tails);
    }

    @After
    public void tearDown() {
        wheel = null;
    }

    @Test
    public void getTotal() {
        PrivateMethodTester tester = new PrivateMethodTester(wheel);
        assertEquals("total", 2, (int) (Integer) tester.invokePrivate("getTotal"));
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
    public void getRandom2() {
        WheelOfFortune.Event<Integer> one = WheelOfFortune.valueOf(1, 4);
        WheelOfFortune.Event<Integer> two = WheelOfFortune.valueOf(2, 5);
        WheelOfFortune.Event<Integer> three = WheelOfFortune.valueOf(3, 4);
        WheelOfFortune.Event<Integer> four = WheelOfFortune.valueOf(4, 3);
        WheelOfFortune.Event<Integer> five = WheelOfFortune.valueOf(5, 2);
        WheelOfFortune<Integer> wheel = new WheelOfFortune<>(0L, one, two, three, four, five);
        assertEquals(Integer.valueOf(2), wheel.get());
    }

    @Test
    public void getWheelOfFortuneBoolean() {
        WheelOfFortune.Event<Boolean> t = WheelOfFortune.valueOf(true, 1);
        WheelOfFortune.Event<Boolean> f = WheelOfFortune.valueOf(false, 2);
        WheelOfFortune<Boolean> wheel = new WheelOfFortune<>(0L, t, f);
        assertEquals(Boolean.TRUE, wheel.get());
        assertEquals(Boolean.FALSE, wheel.get());
        assertEquals(Boolean.FALSE, wheel.get());
        assertEquals(Boolean.FALSE, wheel.get());
        assertEquals(Boolean.FALSE, wheel.get());
        assertEquals(Boolean.FALSE, wheel.get());
        assertEquals(Boolean.FALSE, wheel.get());
        assertEquals(Boolean.TRUE, wheel.get());
    }

    @Test // Slow
    public void videoPoker() {
        WheelOfFortune.Event<String> highCard = WheelOfFortune.valueOf("highCard", 1302540);
        WheelOfFortune.Event<String> pair = WheelOfFortune.valueOf("pair", 1098240);
        WheelOfFortune.Event<String> twoPair = WheelOfFortune.valueOf("twoPair", 123552);
        WheelOfFortune.Event<String> trips = WheelOfFortune.valueOf("trips", 54912);
        WheelOfFortune.Event<String> straight = WheelOfFortune.valueOf("straight", 10200);
        WheelOfFortune.Event<String> flush = WheelOfFortune.valueOf("flush", 5108);
        WheelOfFortune.Event<String> fullHouse = WheelOfFortune.valueOf("fullHouse", 3744);
        WheelOfFortune.Event<String> quads = WheelOfFortune.valueOf("quads", 624);
        WheelOfFortune.Event<String> straightFlush = WheelOfFortune.valueOf("straightFlush", 36);
        WheelOfFortune.Event<String> royal = WheelOfFortune.valueOf("royal", 4);
        WheelOfFortune<String> wheel1 = new WheelOfFortune<>(highCard, pair, twoPair, trips, straight, flush, fullHouse, quads, straightFlush, royal);
        for (int j = 0; j < 10; j++) {
            FrequencyCounter<String> frequencyCounter = new FrequencyCounter<>();
            for (int i = 0; i < 1000000; i++) frequencyCounter.increment(wheel1.get());
            assertEquals(50.1, frequencyCounter.relativeFrequencyAsPercentage("highCard"), 1.6);
            assertEquals(5, frequencyCounter.relativeFrequencyAsPercentage("twoPair"), 1);
            assertEquals(2, frequencyCounter.relativeFrequencyAsPercentage("trips"), 0.7);
        }
    }

    @Test
    public void valueOf() {
        WheelOfFortune.Event<String> event = WheelOfFortune.valueOf("event", 99);
        assertEquals(new WheelOfFortune.Event<>("event", 99), event);
    }
}