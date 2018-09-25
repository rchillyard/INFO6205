package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class FrequencyCounterTest {
    @Test public void testIncrement0() {
        FrequencyCounter<String> fc = new FrequencyCounter<>();
        String x = "X";
        assertEquals(0,fc.get(x));
        fc.increment(x);
        assertEquals(1,fc.get(x));
    }
    
    @Test public void testIncrement1() {
        FrequencyCounter<Integer> fc = new FrequencyCounter<>();
        int x = 1;
        assertEquals(0,fc.get(x));
        fc.increment(x);
        fc.increment(x);
        fc.increment(x);
        assertEquals(3,fc.get(x));
    }
    
    @Test public void testIncrement2() {
        FrequencyCounter<String> fc = new FrequencyCounter<>();
        String x = "hello";
        assertEquals(0,fc.get(x));
        
        String y = "HELLO";
        assertEquals(0,fc.get(y));
        
        fc.increment("HELLO");
        assertEquals(1,fc.get(y));
        
        assertEquals(0,fc.get("heLLo"));
    }
    
     @Test public void testIncrement3() {
        FrequencyCounter<Double> fc = new FrequencyCounter<>();
        Double x = 5.0;
        assertEquals(0,fc.get(x));
        
        fc.increment(x);
        assertEquals(1,fc.get(x));
    }
    
    @Test public void testIncrement4() {
        FrequencyCounter<Integer> fc = new FrequencyCounter<>();
        int x = 1;
        assertEquals(0,fc.get(x));
        int y = 2;
        assertEquals(0,fc.get(y));
        
        int i=0;
        for(;i<10;i++) {
        	if(i%2 == 0)
        		fc.increment(x);
        	else
        		fc.increment(y);
        }
        assertEquals(5,fc.get(x));
        assertEquals(5,fc.get(x));
    }
}
