package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void testPut6() {
        Solution<String, Integer> st = new Solution<>();
        st.put("A", 1);
        st.put("B", 4);
        st.put("C", 7);
        st.put("D", 9);
        st.put("E", 33);
        st.put("F", 0);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(33,(int)st.get("E"));
    }
    
        @Test
    public void testPut3() {
        Solution<Integer, Integer> st = new Solution<>();
        st.put(1, 1);
        st.put(3, 4);
        st.put(3, 0);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(0,(int)st.get(3));
    }

    /**
     * Test of delete method, of class SequentialSearchST.
     */
    @Test
    public void testDelete3() {
        Solution<Integer, Integer> st = new Solution<>();
        st.put(1, 1);
        st.put(2, 4);
        st.put(3, 0);
        // TODO review the generated test code and remove the default call to fail.
        st.delete(2);
        assertEquals(null,st.get(2));
        st.delete(3);
        assertEquals(1,st.size());
        
    }
    
        @Test
    public void testDelete5() {
        Solution<String, Integer> st = new Solution<>();
        st.put("A", 1);
        st.put("B", 4);
        st.put("C", 7);
        st.put("D", 9);
        st.put("E", 33);
        // TODO review the generated test code and remove the default call to fail.
        st.delete("D");
        assertEquals(null,st.get("D"));
        st.delete("D");
        assertEquals(4,st.size());
        
    }
}