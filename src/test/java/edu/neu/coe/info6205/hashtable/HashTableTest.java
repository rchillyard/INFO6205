/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.hashtable;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashTableTest {
    @Test public void testHashTable0() {
        final HashTable hashTable = new HashTable(2);
        assertEquals(0,hashTable.size);
        assertTrue(hashTable.check(1,2));
    }
    @Test public void testHashTable1() {
        final HashTable hashTable = new HashTable(3);
        assertTrue(hashTable.check(2,4));
        assertNull(hashTable.getValue("Hello"));
    }
    @Test public void testHashTable2() {
        final HashTable hashTable = new HashTable(4);
        assertTrue(hashTable.check(2,4));
        hashTable.put("Hello","World!");
        assertEquals(1,hashTable.size);
        assertNotNull(hashTable.getValue("Hello"));
        assertEquals("World!", hashTable.getValue("Hello"));
    }
    @Test public void testHashTable3() {
        final HashTable hashTable = new HashTable(4);
        assertTrue(hashTable.check(2,4));
        hashTable.put("Hello0","World!0");
        hashTable.show();
        hashTable.put("Hello1","World!1");
        hashTable.show();
        assertEquals(2,hashTable.size);
        assertNotNull(hashTable.getValue("Hello0"));
        assertNotNull(hashTable.getValue("Hello1"));
        assertEquals("World!0", hashTable.getValue("Hello0"));
        assertEquals("World!1", hashTable.getValue("Hello1"));
    }
}
