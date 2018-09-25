package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DijkstraStackTest {
	@Test
    public void testEmpty() {
		DijkstraStack<String> stack = new DijkstraStack<>(10);
		assertTrue(stack.isEmpty());
    }
	
	@Test
    public void testPush() {
		DijkstraStack<String> stack = new DijkstraStack<>(10);
		stack.push("a");
		stack.push("b");
		stack.push("c");
		
		assertEquals("c",stack.pop());
    }
	
	@Test
    public void testSize() {
		DijkstraStack<String> stack = new DijkstraStack<>(10);
		stack.push("a");
		stack.push("b");
		stack.push("c");
		
		assertEquals(10,stack.size());
    }
	
	@Test
    public void testPop() {
		DijkstraStack<String> stack = new DijkstraStack<>(10);
		stack.push("a");
		stack.push("b");
		stack.push("c");
		stack.pop();
		stack.pop();
		stack.pop();
		
		assertTrue(stack.isEmpty());
		assertEquals(null,stack.pop());
    }
	
	@Test
    public void testPopElement() {
		DijkstraStack<String> stack = new DijkstraStack<>(10);
		stack.push("a");
		stack.push("b");
		stack.push("c");
		stack.pop();
		stack.pop();
		
		
		assertEquals("a",stack.pop());
    }
	
	@Test
    public void testPeek() {
		DijkstraStack<String> stack = new DijkstraStack<>(10);
		stack.push("a");
		stack.push("b");
		stack.push("c");
		
		assertEquals("c",stack.peek());
    }
	
	@Test
    public void testIsFull() {
		DijkstraStack<String> stack = new DijkstraStack<>(3);
		stack.push("a");
		stack.push("b");
		stack.push("c");
		
		assertTrue(stack.isFull());
    }
	
	@Test
    public void testGrow() {
		DijkstraStack<String> stack = new DijkstraStack<>(3);
		assertEquals(3,stack.size());
		
		stack.push("a");
		stack.push("b");
		stack.push("c");
		stack.push("d");
		
		assertEquals(6,stack.size());
	}
	
	@Test
    public void testEvaluate() {
		 DijkstraStack<String> operators = new DijkstraStack<>(10);
		 DijkstraStack<Integer> values = new DijkstraStack<>(10);
		 
		 String expr = "( 1 + ( 2 + 3 ) )";
		 
		 DijkstraStack.evaluate(operators, values, expr);
		 
		 assertTrue(values.pop() == 6);
	}
	
}
