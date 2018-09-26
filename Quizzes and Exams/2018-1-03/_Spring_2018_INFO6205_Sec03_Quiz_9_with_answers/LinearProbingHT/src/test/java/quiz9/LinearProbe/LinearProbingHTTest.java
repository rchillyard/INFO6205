package quiz9.LinearProbe;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class LinearProbingHTTest 
    extends TestCase
{

    public void test1()
    {
    		LinearProbingHT<String, String> ht = new LinearProbingHT<String, String>();
		ht.put("Name", "Full name");
    		assertTrue( ht.get("Name").equals("Full name") );
    		assertTrue(ht.checkValue(11).equals("Full name"));
    }
    
    public void test2()
    {
    		LinearProbingHT<String, String> ht = new LinearProbingHT<String, String>();
		ht.put("Name", "Full name");
		ht.put("DOB", "Date of birth");
		ht.put("Cohort", "Expected graduation year");
		assertTrue(ht.get("Name").equals("Full name"));
		assertTrue(ht.get("DOB").equals("Date of birth"));
		assertTrue(ht.checkValue(7).equals("Date of birth"));
    }
    
    public void test3()
    {
    		LinearProbingHT<String, String> ht = new LinearProbingHT<String, String>();
		ht.put("Name", "Full name");
		ht.put("Name", "First and last name");
		assertTrue(ht.get("Name").equals("First and last name"));
		assertTrue(ht.checkValue(11).equals("First and last name"));
    }
    
    public void test4()
    {
    		LinearProbingHT<String, Integer> ht = new LinearProbingHT<String, Integer>();
    		ht.put("b", 7);
    		ht.put("r", 5);
    		assertTrue(ht.get("b") == 7);
    		assertTrue(ht.checkValue(2) == 7);
    		assertTrue(ht.get("r") == 5);
    		assertTrue(ht.checkValue(3) == 5);
    }
    
    public void test5()
    {
    		LinearProbingHT<String, Integer> ht = new LinearProbingHT<String, Integer>();
		ht.put("b", 7);
		ht.put("c", 3);
		ht.put("d", 2);
		ht.put("r", 5);
		assertTrue(ht.get("b") == 7);
		assertTrue(ht.get("r") == 5);
    		assertTrue(ht.checkValue(5) == 5);
    }
    
    public void test6()
    {
    		LinearProbingHT<String, Integer> ht = new LinearProbingHT<String, Integer>();
		ht.put("b", 7);
		ht.put("c", 3);
		ht.put("d", 2);
		ht.put("r", 1);
		ht.put("Hello", 5);
		ht.put("e", 9);
		assertTrue(ht.checkValue(2) == 7);
		assertTrue(ht.checkValue(5) == 1);
    		assertTrue(ht.checkValue(6) == 5);
    		assertTrue(ht.checkValue(7) == 9);
    	
    }
}
