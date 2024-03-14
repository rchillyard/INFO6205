package edu.neu.coe.info6205.graphs.dag;

import edu.neu.coe.info6205.SizedIterable;
import edu.neu.coe.info6205.bqs.BQSException;
import edu.neu.coe.info6205.bqs.Stack;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DiGraphTest {

    @Test
    public void testVertices() {
        DiGraph<String, Integer> graph = new DiGraph<>();
        graph.addEdge(new Edge<>("A", "B", 3));
        SizedIterable<String> vertices = graph.vertices();
        assertEquals(2, vertices.size());
    }

    @Test
    public void testAdjacent() {
        DiGraph<String, Integer> graph = new DiGraph<>();
        Edge<String, Integer> edgeAB = new Edge<>("A", "B", 3);
        graph.addEdge(edgeAB);
        Iterable<Edge<String, Integer>> edges = graph.adjacent("A");
        Iterator<Edge<String, Integer>> iterator = edges.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(edgeAB, iterator.next());
    }

    @Test
    public void testEdges() {
        DiGraph<String, Integer> graph = new DiGraph<>();
        graph.addEdge(new Edge<>("A", "B", 3));
        SizedIterable<Edge<String, Integer>> edges = graph.edges();
        assertEquals(1, edges.size());
    }

    @Test
    public void testKernelDAG() {
        DiGraph<String, Integer> graph = creatTestGraph();
        SizedIterable<Edge<String, Integer>> edges0 = graph.edges();
        assertEquals(7, edges0.size());
        final DAG<DiGraph.Kernel<String>, Integer> kernelDAG = graph.kernelDAG();
        final SizedIterable<Edge<DiGraph.Kernel<String>, Integer>> edges1 = kernelDAG.edges();
        assertEquals(2, edges1.size());
        final SizedIterable<DiGraph.Kernel<String>> kernels = kernelDAG.vertices();
        assertEquals(3, kernels.size());
        for (DiGraph.Kernel<String> kernel : kernels) {
            final String s = kernel.toString();
            assertTrue(s.equals("[F]") || s.equals("[D, E]") || s.equals("[A, B, C]"));
        }
    }

    @Test
    public void testReversePostOrderDFS1() throws BQSException {
        // FIXME
        DiGraph<String, Integer> graph = creatTestGraph();
        final Stack<String> reversePostOrder = graph.reversePostOrderDFS();
        assertEquals("A", reversePostOrder.pop());
        String pop1 = reversePostOrder.pop();
        String pop2 = reversePostOrder.pop();
        String pop3 = reversePostOrder.pop();
        String pop4 = reversePostOrder.pop();
        String pop5 = reversePostOrder.pop();
        assertTrue(reversePostOrder.isEmpty());
//        assertEquals("D", pop1);
//        assertEquals("F", pop2);
//        assertEquals("E", pop3);
//        assertEquals("B", pop4);
//        assertEquals("C", pop5);
    }

    @Test
    public void testReversePostOrderDFS2() throws BQSException {
        DiGraph<String, Integer> graph = creatTestGraph();
        final Stack<String> reversePostOrder = graph.reverse().reversePostOrderDFS();
        assertEquals("F", reversePostOrder.pop());
        assertEquals("D", reversePostOrder.pop());
        assertEquals("E", reversePostOrder.pop());
        assertEquals("A", reversePostOrder.pop());
        assertEquals("C", reversePostOrder.pop());
        assertEquals("B", reversePostOrder.pop());
        assertTrue(reversePostOrder.isEmpty());
    }

    private DiGraph<String, Integer> creatTestGraph() {
        DiGraph<String, Integer> graph = new DiGraph<>();
//         /------->---------D------->------F
//        A--->B           ^  |
//         <-   |          | ->
//          \   >           E
//           ---C
        graph.addEdge(new Edge<>("A", "B", 1));
        graph.addEdge(new Edge<>("B", "C", 2));
        graph.addEdge(new Edge<>("C", "A", 3));
        graph.addEdge(new Edge<>("A", "D", 4));
        graph.addEdge(new Edge<>("D", "E", 5));
        graph.addEdge(new Edge<>("E", "D", 6));
        graph.addEdge(new Edge<>("D", "F", 7));
        return graph;
    }

    @Test
    public void testToString() {
        DiGraph<String, Integer> graph = new DiGraph<>();
        graph.addEdge(new Edge<>("A", "B", 3));
        assertEquals("{A=Bag_Array{items=[3: A->B], count=1}, B=Bag_Array{items=[], count=0}}", graph.toString());
    }
}