/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.symbolTable.hashtable;

import edu.neu.coe.info6205.symbolTable.ST;
import org.junit.Test;

import java.util.Random;
import java.util.Set;

import static org.junit.Assert.*;

public class HashTableSCTest {

    static class BadClass {
        @Override
        public int hashCode() {
            return 17;
        }

        @Override
        public String toString() {
            return "badClass";
        }
    }

    @Test
    public void testHashTable0() {
        final ST<Object, Object> hashTable = new HashTable_SC<>();
        assertEquals(0, hashTable.size());
        assertTrue(hashTable.isEmpty());
    }

    @Test
    public void testHashTable1() {
        final HashTable_SC<String, Object> hashTable = new HashTable_SC<>();
        assertNull(hashTable.get("Hello"));
    }

    @Test
    public void testHashTable2() {
        final HashTable_SC<String, String> hashTable = new HashTable_SC<>();
        hashTable.put("Hello", "World!");
        assertEquals(1, hashTable.size());
        assertNotNull(hashTable.get("Hello"));
        assertEquals("World!", hashTable.get("Hello"));
    }

    @Test
    public void testHashTable2a() {
        final HashTable_SC<BadClass, String> hashTable = new HashTable_SC<>();
        BadClass badClass1 = new BadClass();
        hashTable.put(badClass1, "World!1");
        assertEquals(1, hashTable.size());
        BadClass badClass2 = new BadClass();
        hashTable.put(badClass2, "World!2");
        assertEquals("World!2", hashTable.get(badClass2));
        hashTable.put(badClass2, "World!2a");
        assertEquals("World!1", hashTable.get(badClass1));
        assertEquals("World!2a", hashTable.get(badClass2));
        assertEquals(2, hashTable.size());
        assertEquals("1: [badClass:World!2a, badClass:World!1]\n", hashTable.toString());
    }


    @Test
    public void testHashTable3() {
        final HashTable_SC<String, String> hashTable = new HashTable_SC<>();
        hashTable.put("Hello0", "World!0");
        hashTable.put("Hello1", "World!1");
        assertEquals(2, hashTable.size());
        assertNotNull(hashTable.get("Hello0"));
        assertEquals("World!0", hashTable.get("Hello0"));
        assertEquals("World!1", hashTable.get("Hello1"));
        Set<String> keys = hashTable.keys();
        assertEquals(2, keys.size());
    }

    @Test
    public void testHashTable4() {
        Random random = new Random(0L);
        int capacity = 32;
        final HashTable_SC<String, String> hashTable = new HashTable_SC<>(capacity);
        for (int i = 0; i < capacity; i++)
            hashTable.put("" + random.nextInt(100), "" + random.nextFloat());
        assertEquals(29, hashTable.size());
        Set<String> keys = hashTable.keys();
        assertEquals(29, keys.size());
    }

    @Test
    public void testHashTable4a() {
        Random random = new Random(2L);
        int m = 8192;
        long freeMemory0 = Runtime.getRuntime().freeMemory();
        System.out.println("free memory: " + freeMemory0);
        final ST<String, String> ht0 = new HashTable_SC<>(m);
        assertTrue(ht0.isEmpty());
        long freeMemory1 = Runtime.getRuntime().freeMemory();
        System.out.println("used memory (empty hash table with m " + m + "): " + (freeMemory0 - freeMemory1));
        final ST<String, String> ht1 = new HashTable_SC<>(m);
        assertTrue(ht1.isEmpty());
        long freeMemory2 = Runtime.getRuntime().freeMemory();
        System.out.println("used memory (empty hash table with m " + m + "): " + (freeMemory1 - freeMemory2));
        for (int i = 0; i < m * 2; i++)
            ht1.put("" + random.nextInt(100000), "" + random.nextFloat());
        assertEquals(15149, ht1.size()); // for m = 8k
//        assertEquals(32768, ht1.size()); // for m = 16k
        long freeMemory3 = Runtime.getRuntime().freeMemory();
        System.out.println("used memory (hash table with alpha = 2, m " + m + "): " + (freeMemory2 - freeMemory3));
        Set<String> keys = ht1.keys();
        System.out.println(keys.size());
        System.out.println(ht1);
    }

}
