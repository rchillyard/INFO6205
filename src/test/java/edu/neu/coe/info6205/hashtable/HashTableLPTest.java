/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.hashtable;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashTableLPTest {
    @Test
    public void testGetIndex0() {
        assertEquals(2, HashTable_LP.getIndex("Hello0", 2));
        assertEquals(3, HashTable_LP.getIndex("Hello1", 2));
        assertEquals(0, HashTable_LP.getIndex("Hello2", 2));
        assertEquals(1, HashTable_LP.getIndex("Hello3", 2));
        assertEquals(6, HashTable_LP.getIndex("Hello0", 3));
        assertEquals(7, HashTable_LP.getIndex("Hello1", 3));
        assertEquals(0, HashTable_LP.getIndex("Hello2", 3));
        assertEquals(1, HashTable_LP.getIndex("Hello3", 3));
        assertEquals(2, HashTable_LP.getIndex("Hello4", 3));
        assertEquals(3, HashTable_LP.getIndex("Hello5", 3));
        assertEquals(4, HashTable_LP.getIndex("Hello6", 3));
        assertEquals(5, HashTable_LP.getIndex("Hello7", 3));
    }

    @Test
    public void testHashTable0() {
        final HashTable_LP<Object, Object> hashTable = new HashTable_LP<>(2);
        assertEquals(0, hashTable.size);
        assertTrue(hashTable.check(1, 2));
    }

    @Test
    public void testHashTable1() {
        final HashTable_LP<String, Object> hashTable = new HashTable_LP<>(3);
        assertTrue(hashTable.check(2, 4));
        assertNull(hashTable.getValueMaybe("Hello"));
    }

    @Test
    public void testHashTable2() {
        final HashTable_LP<String, String> hashTable = new HashTable_LP<>(4);
        assertTrue(hashTable.check(2, 4));
        hashTable.put("Hello", "World!");
        assertEquals(1, hashTable.size);
        assertNotNull(hashTable.get("Hello"));
        assertEquals("World!", hashTable.get("Hello"));
    }

    @Test
    public void testHashTable3() {
        final HashTable_LP<String, String> hashTable = new HashTable_LP<>(4);
        assertTrue(hashTable.check(2, 4));
        hashTable.put("Hello0", "World!0");
        hashTable.show();
        hashTable.put("Hello1", "World!1");
        hashTable.show();
        assertEquals(2, hashTable.size);
        assertNotNull(hashTable.get("Hello0"));
        assertNotNull(hashTable.getValueMaybe("Hello1"));
        assertEquals("World!0", hashTable.get("Hello0"));
        assertEquals("World!1", hashTable.get("Hello1"));
    }

    //  TODO  @Test(expected = java.lang.AssertionError.class)
    public void testHashTable4() {
        final HashTable_LP<String, Object> hashTable = new HashTable_LP<>(3);
        hashTable.get("Hello");
    }
}
