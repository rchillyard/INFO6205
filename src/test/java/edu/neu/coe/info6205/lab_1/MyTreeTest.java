package edu.neu.coe.info6205.lab_1;

import com.google.common.collect.ImmutableList;
import edu.neu.coe.info6205.util.PrivateMethodTester;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyTreeTest {

		@Test
		public void Node0() {
				MyTree<Integer> tree = new MyTree<>(1);
				Integer x = tree.getRoot().x;
				assertEquals("x", Integer.valueOf(1), x);
		}

		@Test
		public void Node1() {
				MyTree<Integer> tree = new MyTree<>(1).addChild(new MyTree.Node<Integer>(2));
				ImmutableList<MyTree.Node<Integer>> x = tree.getRoot().children;
				assertTrue(x.iterator().hasNext());
				assertEquals(Integer.valueOf(2), x.iterator().next().x);
		}

}