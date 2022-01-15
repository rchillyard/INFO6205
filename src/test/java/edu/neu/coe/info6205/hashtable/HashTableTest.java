/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.hashtable;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashTableTest {
    @Test
    public void testGetIndex0() {
        assertEquals(2, HashTable.getIndex("Hello0", 2));
        assertEquals(3, HashTable.getIndex("Hello1", 2));
        assertEquals(0, HashTable.getIndex("Hello2", 2));
        assertEquals(1, HashTable.getIndex("Hello3", 2));
        assertEquals(6, HashTable.getIndex("Hello0", 3));
        assertEquals(7, HashTable.getIndex("Hello1", 3));
        assertEquals(0, HashTable.getIndex("Hello2", 3));
        assertEquals(1, HashTable.getIndex("Hello3", 3));
        assertEquals(2, HashTable.getIndex("Hello4", 3));
        assertEquals(3, HashTable.getIndex("Hello5", 3));
        assertEquals(4, HashTable.getIndex("Hello6", 3));
        assertEquals(5, HashTable.getIndex("Hello7", 3));
    }

    @Test
    public void testHashTable0() {
        final HashTable hashTable = new HashTable(2);
        assertEquals(0, hashTable.size);
        assertTrue(hashTable.check(1, 2));
    }

    @Test
    public void testHashTable1() {
        final HashTable hashTable = new HashTable(3);
        assertTrue(hashTable.check(2, 4));
        assertNull(hashTable.getValueMaybe("Hello"));
    }

    @Test
    public void testHashTable2() {
        final HashTable hashTable = new HashTable(4);
        assertTrue(hashTable.check(2, 4));
        hashTable.put("Hello", "World!");
        assertEquals(1, hashTable.size);
        assertNotNull(hashTable.getValue("Hello"));
        assertEquals("World!", hashTable.getValue("Hello"));
    }

    @Test
    public void testHashTable3() {
        final HashTable hashTable = new HashTable(4);
        assertTrue(hashTable.check(2, 4));
        hashTable.put("Hello0", "World!0");
        hashTable.show();
        hashTable.put("Hello1", "World!1");
        hashTable.show();
        assertEquals(2, hashTable.size);
        assertNotNull(hashTable.getValue("Hello0"));
        assertNotNull(hashTable.getValueMaybe("Hello1"));
        assertEquals("World!0", hashTable.getValue("Hello0"));
        assertEquals("World!1", hashTable.getValue("Hello1"));
    }

    //  TODO  @Test(expected = java.lang.AssertionError.class)
    public void testHashTable4() {
        final HashTable hashTable = new HashTable(3);
        hashTable.getValue("Hello");
    }
}
