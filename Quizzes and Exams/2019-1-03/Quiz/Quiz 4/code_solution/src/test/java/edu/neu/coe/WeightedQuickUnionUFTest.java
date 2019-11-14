package edu.neu.coe;

import org.junit.Test;

import edu.neu.coe.WeightedQuickUnionUF;
import junit.framework.TestCase;

public class WeightedQuickUnionUFTest 
    extends TestCase
{
	@Test
	public void test1() {
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(9);
		assertEquals(2,uf.find(2));
	}

	@Test
	public void test2() {
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(9);
		uf.union(1, 2);
		assertTrue(uf.connected(1, 2));
	}
	
	@Test
	public void test3() {
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(9);
		uf.union(1, 2);
        uf.union(2, 2);
        uf.union(1, 1);
        uf.union(2, 1);
		assertEquals(8,uf.count());
	}
	
	@Test
	public void test4() {
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(9);
		uf.union(1, 2);
		assertEquals(1,uf.find(2));
	}
	
	@Test
	public void test5() {
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(9);
		uf.union(2, 1);
		uf.union(1, 3);
		assertEquals(2, uf.find(3));
	}
	
	@Test
	public void test6() {
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(9);
		uf.union(1, 4);
		uf.union(3, 4);
		uf.union(2, 3);
		uf.union(5, 6);
		uf.union(2, 5);
        uf.union(1, 4);
		assertEquals(1,uf.find(6));
        assertEquals(4,uf.count());
	}
	
}
