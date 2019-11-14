package com.example;

import java.util.HashMap;
import java.util.Map;

public class FrequencyCounter<K> {

    public FrequencyCounter(Map<K,Integer> map) {
        this.map = map;
    }

    public FrequencyCounter() {
        this(new HashMap<>());
    }

    public int get(Object key) {
    	// TODO :: Get the value associated with the key from the map. The value will be the 
        // will be frequency of the key. Hint: you will have to change the default return provided.
        Integer i=map.get(key);
        if(i==null)
        {
            i=0;
        }
        return i;
    }

    public void increment(K s) {
    	// TODO :: This function will increment the frequency of a particular and then put the new
        // back into the map.
        int i=get(s);
        map.put(s,i+1);
    }

    private final Map<K,Integer> map;
    
    
    // Safe gaurd against accidental Run Main button click.
    // You will not need to use the main method. You should run the unit tests.
    public static void main(String[] args){
        System.out.println("Run the unit tests !");
    }

}
