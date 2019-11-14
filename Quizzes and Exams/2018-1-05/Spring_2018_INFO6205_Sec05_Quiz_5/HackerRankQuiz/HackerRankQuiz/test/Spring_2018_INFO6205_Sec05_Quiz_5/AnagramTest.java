/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spring_2018_INFO6205_Sec05_Quiz_5;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ankit
 */
public class AnagramTest {
    
    public AnagramTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isAnagram method, of class Anagram.
     */
    @Test
    public void testIsAnagram0() {
        System.out.println("isAnagram");
        String s = "a";
        String t = "a";
        boolean expResult = true;
        boolean result = Anagram.isAnagram(s, t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
        /**
     * Test of isAnagram method, of class Anagram.
     */
    @Test
    public void testIsAnagram1() {
        System.out.println("isAnagram");
        String s = "ab";
        String t = "ba";
        boolean expResult = true;
        boolean result = Anagram.isAnagram(s, t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
        /**
     * Test of isAnagram method, of class Anagram.
     */
    @Test
    public void testIsAnagram2() {
        System.out.println("isAnagram");
        String s = "a";
        String t = "aa";
        boolean expResult = false;
        boolean result = Anagram.isAnagram(s, t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
        /**
     * Test of isAnagram method, of class Anagram.
     */
    @Test
    public void testIsAnagram3() {
        System.out.println("isAnagram");
        String s = "aab";
        String t = "abb";
        boolean expResult = false;
        boolean result = Anagram.isAnagram(s, t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
        /**
     * Test of isAnagram method, of class Anagram.
     */
    @Test
    public void testIsAnagram4() {
        System.out.println("isAnagram");
        String s = "rat";
        String t = "car";
        boolean expResult = false;
        boolean result = Anagram.isAnagram(s, t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
        /**
     * Test of isAnagram method, of class Anagram.
     */
    @Test
    public void testIsAnagram5() {
        System.out.println("isAnagram");
        String s = "anagram";
        String t = "nagaram";
        boolean expResult = true;
        boolean result = Anagram.isAnagram(s, t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    
}
