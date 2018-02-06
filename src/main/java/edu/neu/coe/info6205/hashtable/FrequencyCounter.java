/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.hashtable;

import java.util.HashMap;

public class FrequencyCounter {

    private final ST<String,Integer> map;

    public FrequencyCounter(ST<String,Integer> map) {
        this.map = map;
    }

    public void increment(String s) {
        Integer x = map.get(s);
        if (x==null) x = 0;
        map.put(s,x+1);
    }

    public String toString() {
        return map.toString();
    }

    public static void main(String[] args) {
        FrequencyCounter counter = new FrequencyCounter(new STMap<>(new HashMap<>()));

        for (String arg: args) counter.increment(arg);

        System.out.println(counter.toString());
    }
}
