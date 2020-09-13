/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.graphs.undirected;

import org.junit.Test;

import static org.junit.Assert.*;

public class GraphTest {

    /**
     * Test method for DAG()
     */
    @Test
    public void testGraph() {
        EdgeGraph<Integer, Double> target = new Graph_Edges<>();
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
        EdgeGraph<Integer, Double> target = new Graph_Edges<>();
        Edge<Integer, Double> edge = new Edge<>(1, 2, Math.PI);
        target.addEdge(edge);
        assertEquals(1, target.edges().size());
        assertEquals(2, target.vertices().size());
        assertTrue(target.edges().iterator().hasNext());
        assertTrue(target.vertices().iterator().hasNext());
        assertEquals(edge, target.edges().iterator().next());
        assertEquals(new Integer(1), target.vertices().iterator().next());
    }

//    /**
//     * Test method for DFS
//     */
//    @Test
//    public void testDFS() {
//        Queue<Integer> preOrder = new LinkedList<>();
//        Queue<Integer> postOrder = new LinkedList<>();
//        Stack<Integer> reversePostOrder = new Stack_LinkedList<>();
//        EdgeGraph<Integer, Double> target = new Graph_Edges<>();
//        Consumer<Integer> pre = (v) -> {
//            preOrder.add(v);
//        };
//        Consumer<Integer> post = (v) -> {
//            postOrder.add(v);
//            reversePostOrder.push(v);
//        };
//
//        target.dfs(0, pre, post);
//        assertEquals(Integer.valueOf(0), ((LinkedList<Integer>) preOrder).getFirst());
//        assertEquals(Integer.valueOf(5), ((LinkedList<Integer>) preOrder).getLast());
//        assertEquals(Integer.valueOf(4), ((LinkedList<Integer>) postOrder).getFirst());
//        assertEquals(Integer.valueOf(0), ((LinkedList<Integer>) postOrder).getLast());
//        try {
//            assertEquals(Integer.valueOf(0), (reversePostOrder).pop());
//            assertEquals(Integer.valueOf(5), (reversePostOrder).pop());
//            assertEquals(Integer.valueOf(2), (reversePostOrder).pop());
//            assertEquals(Integer.valueOf(1), (reversePostOrder).pop());
//            assertEquals(Integer.valueOf(4), (reversePostOrder).pop());
//        } catch (BQSException e) {
//            e.printStackTrace();
//        }
//    }

    private Graph<Integer, Edge<Integer, Double>> setupStandardDAG() {
        EdgeGraph<Integer, Double> target = new Graph_Edges<>();
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
