/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.graphs.dag;

import edu.neu.coe.info6205.bqs.BQSException;
import edu.neu.coe.info6205.bqs.Stack;
import edu.neu.coe.info6205.bqs.Stack_LinkedList;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.function.Consumer;

import static org.junit.Assert.*;

public class DAGTest {

    /**
     * Test method for DAG()
     */
    @Test
    public void testDAG() {
        DAG<Integer, Double> target = new DAG_Impl<>(new Random(0L));
        assertNotNull(target);
        assertEquals(0, target.edges().size());
        assertEquals(0, target.vertices().size());
        assertFalse(target.edges().iterator().hasNext());
        assertFalse(target.vertices().iterator().hasNext());
    }

    /**
     * Test method for addEdge
     */
    @Test
    public void testAddEdge() {
        DAG_Impl<Integer, Double> target = new DAG_Impl<>(new Random(0L));
        Edge<Integer, Double> edge = new Edge<>(1, 2, Math.PI);
        target.addEdge(edge);
        assertEquals(1, target.edges().size());
        assertEquals(2, target.vertices().size());
        assertTrue(target.edges().iterator().hasNext());
        assertTrue(target.vertices().iterator().hasNext());
        assertEquals(edge, target.edges().iterator().next());
        assertEquals(Integer.valueOf(1), target.vertices().iterator().next());
    }

    /**
     * FIXME this fails because bags are iterated randomly now.
     * <p>
     * Test method for DAG
     */
//    @Test
    public void testDag2() {
        DAG_Impl<Integer, Double> target = setupStandardDAG(new Random(5L));
        assertEquals(11, target.edges().size());
        assertEquals(7, target.vertices().size());
        assertEquals(Integer.valueOf(0), target.edges().iterator().next().getFrom());
        assertEquals(Integer.valueOf(0), target.vertices().iterator().next());
        assertEquals(Integer.valueOf(1), target.adjacent(0).iterator().next().getTo());
        assertFalse(target.adjacent(2).iterator().hasNext());
        assertEquals(Integer.valueOf(4), target.adjacent(1).iterator().next().getTo());
    }

    /**
     * Test method for reverse
     * FIXME this fails because bags are iterated randomly now.
     * <p>
     * NOTE: this belongs in DiGraphTest
     */
//    @Test
    public void testReverse() {
        DiGraph<Integer, Double> target = setupStandardDAG(new Random(5L));
        DiGraph<Integer, Double> integerDAG = target.reverse();
        assertEquals(11, integerDAG.edges().size());
        assertEquals(7, integerDAG.vertices().size());
        assertEquals(Integer.valueOf(0), integerDAG.edges().iterator().next().getFrom());
        assertEquals(Integer.valueOf(0), integerDAG.vertices().iterator().next());
        assertEquals(Integer.valueOf(6), integerDAG.adjacent(0).iterator().next().getTo());
        assertFalse(integerDAG.adjacent(3).iterator().hasNext());
        assertEquals(Integer.valueOf(0), integerDAG.adjacent(1).iterator().next().getTo());
    }

    /**
     * Test method for DFS
     * <p>
     * FIXME
     */
    @Test
    public void testDFS() {
        Queue<Integer> preOrder = new LinkedList<>();
        Queue<Integer> postOrder = new LinkedList<>();
        Stack<Integer> reversePostOrder = new Stack_LinkedList<>();
        DAG_Impl<Integer, Double> target = setupStandardDAG(new Random(0L));
        Consumer<Integer> pre = preOrder::add;
        Consumer<Integer> post = (v) -> {
            postOrder.add(v);
            reversePostOrder.push(v);
        };

        target.dfs(0, pre, post);
        assertEquals(Integer.valueOf(0), ((LinkedList<Integer>) preOrder).getFirst());
        assertEquals(Integer.valueOf(5), ((LinkedList<Integer>) preOrder).getLast());
        assertEquals(Integer.valueOf(4), ((LinkedList<Integer>) postOrder).getFirst());
        assertEquals(Integer.valueOf(0), ((LinkedList<Integer>) postOrder).getLast());
        try {
            assertEquals(Integer.valueOf(0), (reversePostOrder).pop());
            assertEquals(Integer.valueOf(5), (reversePostOrder).pop());
            assertEquals(Integer.valueOf(2), (reversePostOrder).pop());
            assertEquals(Integer.valueOf(1), (reversePostOrder).pop());
            assertEquals(Integer.valueOf(4), (reversePostOrder).pop());
        } catch (BQSException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method for sorted
     */
    @Test
    public void testSorted() {
        DAG<Integer, Double> target = setupStandardDAG(new Random(0L));
        Iterable<Integer> sorted = target.sorted();
        System.out.println(sorted);
        Iterator<Integer> iterator = sorted.iterator();
        assertEquals(Integer.valueOf(3), iterator.next());
        assertEquals(Integer.valueOf(6), iterator.next());
        assertEquals(Integer.valueOf(0), iterator.next());
        assertEquals(Integer.valueOf(5), iterator.next());
        assertEquals(Integer.valueOf(2), iterator.next());
        assertEquals(Integer.valueOf(1), iterator.next());
        assertEquals(Integer.valueOf(4), iterator.next());
        assertFalse(iterator.hasNext());
    }

    /**
     * Test method for sorted with cycle.
     * TODO reinstate this test but it the result is not really predictable.
     */
    public void testSortedWithCycle() {
        DAG_Impl<Integer, Double> target = setupStandardDAG(new Random(0L));
        target.addEdge(new Edge<>(4, 3, 1.0));
        Iterable<Integer> sorted = target.sorted();
        System.out.println(sorted);
        Iterator<Integer> iterator = sorted.iterator();
        assertEquals(Integer.valueOf(3), iterator.next());
        assertEquals(Integer.valueOf(6), iterator.next());
        assertEquals(Integer.valueOf(0), iterator.next());
        assertEquals(Integer.valueOf(5), iterator.next());
        assertEquals(Integer.valueOf(2), iterator.next());
        assertEquals(Integer.valueOf(1), iterator.next());
        assertEquals(Integer.valueOf(4), iterator.next());
        assertFalse(iterator.hasNext());
    }

    private DAG_Impl<Integer, Double> setupStandardDAG(Random random) {
        DAG_Impl<Integer, Double> target = new DAG_Impl<>(random);
        target.addEdge(0, 1, 1.0);
        target.addEdge(0, 2, 1.0);
        target.addEdge(0, 5, 1.0);
        target.addEdge(1, 4, 1.0);
        target.addEdge(3, 2, 1.0);
        target.addEdge(3, 4, 1.0);
        target.addEdge(3, 5, 1.0);
        target.addEdge(3, 6, 1.0);
        target.addEdge(5, 2, 1.0);
        target.addEdge(6, 0, 1.0);
        target.addEdge(6, 4, 1.0);
        return target;
    }

}