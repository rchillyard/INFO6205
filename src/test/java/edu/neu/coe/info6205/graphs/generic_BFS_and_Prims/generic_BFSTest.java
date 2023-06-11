package edu.neu.coe.info6205.graphs.generic_BFS_and_Prims;

import edu.neu.coe.info6205.graphs.BFS_and_prims.BFS;
import edu.neu.coe.info6205.graphs.generic_BFS_and_prims.GBFS;
import edu.neu.coe.info6205.graphs.generic_BFS_and_prims.Graph;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class generic_BFSTest {


    //Undirected graphs

    @Test
    public void testbfs1() {
        Graph g = new Graph<String>(4);
        g.addVertex("a");
        g.addVertex("b");
        g.addVertex("c");
        g.addVertex("d");

        g.addEdge("a", "b");
        g.addEdge("a", "c");
        g.addEdge("b", "c");
        //g.addEdge("c", "a");
        g.addEdge("c", "d");
        g.addEdge("d", "d");

        GBFS b = new GBFS(g,"c");

        LinkedList<String> expected = new LinkedList<String>();
        expected.add("c");
        expected.add("a");
        expected.add("b");
        expected.add("d");

        Assert.assertEquals(expected,b.bfs(g,"c"));
    }
    @Test
    public void testbfs2() {
        Graph g = new Graph<String>(4);
        g.addVertex(0);
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        GBFS b = new GBFS(g,2);

        LinkedList<Integer> expected = new LinkedList<Integer>();
        expected.add(2);
        expected.add(0);
        expected.add(1);
        expected.add(3);

        Assert.assertEquals(expected,b.bfs(g,2));
    }
    @Test
    public void testbfs3() {
        Graph g = new Graph<String>(6);
        g.addVertex(0);
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);
        g.addVertex(5);

        g.addEdge(0, 1);
        g.addEdge(3, 5);
        g.addEdge(0, 2);
        g.addEdge(0, 5);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 4);


        GBFS b = new GBFS(g, 0);
        LinkedList<Integer> expected = new LinkedList<Integer>();
        expected.add(0);
        expected.add(1);
        expected.add(2);
        expected.add(5);
        expected.add(3);
        expected.add(4);

        final List bfs = b.bfs(g, 0);
        //bfs.stream().head
        Assert.assertEquals(expected, bfs);

    }

    @Test
    public void testbfs4() {
        Graph g = new Graph<String>(7);

            g.addVertex(0);
            g.addVertex(1);
            g.addVertex(2);
            g.addVertex(3);
            g.addVertex(4);
            g.addVertex(5);
            g.addVertex(6);

        g.addEdge(0, 1);
        g.addEdge(0, 3);
        g.addEdge(0, 4);
        g.addEdge(4, 5);
        g.addEdge(3, 5);
        g.addEdge(1, 2);
        g.addEdge(1, 0);
        g.addEdge(2, 1);
        g.addEdge(4, 1);
        g.addEdge(3, 1);
        g.addEdge(5, 4);
        g.addEdge(5, 3);

        GBFS b = new GBFS(g, 0);

        LinkedList<Integer> expected1 = new LinkedList<Integer>();
        expected1.add(0);
        expected1.add(1);
        expected1.add(3);
        expected1.add(4);
        expected1.add(2);
        expected1.add(5);

        final List bfs = b.bfs(g, 0);

        Assert.assertEquals(expected1,bfs);
    }

    @Test
    public void testbfs5() {
        Graph g = new Graph<String>(6);
        g.addVertex(0);
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);
        g.addVertex(5);

        g.addEdge(0, 2);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 1);
        g.addEdge(4, 0);
        g.addEdge(4, 5);

        GBFS b = new GBFS(g, 0);
        LinkedList<Integer> expected = new LinkedList<Integer>();
        expected.add(0);
        expected.add(2);
        expected.add(1);
        expected.add(4);
        expected.add(3);
        expected.add(5);

        final List bfs = b.bfs(g, 0);
        Assert.assertEquals(expected,bfs);
    }
}
