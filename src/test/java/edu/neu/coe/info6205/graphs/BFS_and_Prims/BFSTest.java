package edu.neu.coe.info6205.graphs.BFS_and_Prims;

import edu.neu.coe.info6205.graphs.BFS_and_prims.BFS;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

public class BFSTest {

    @Test
    public void testbfs1() {
        BFS g = new BFS(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        LinkedList<Integer> expected = new LinkedList<Integer>();
        expected.add(2);
        expected.add(0);
        expected.add(3);
        expected.add(1);

        Assert.assertEquals(expected,g.BFS(2));
    }

    @Test
    public void testbfs2() {
        BFS g = new BFS(7);

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

        LinkedList<Integer> expected1 = new LinkedList<Integer>();
        expected1.add(0);
        expected1.add(1);
        expected1.add(3);
        expected1.add(4);
        expected1.add(2);
        expected1.add(5);

        Assert.assertEquals(expected1,g.BFS(0));
    }

    @Test
    public void testbfs3() {
        BFS g = new BFS(6);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(2, 4);
        g.addEdge(2, 5);

        LinkedList<Integer> expected = new LinkedList<Integer>();
        expected.add(0);
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);

        Assert.assertEquals(expected,g.BFS(0));
    }

    @Test
    public void testbfs4() {
        BFS g = new BFS(6);

        g.addEdge(0, 2);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 1);
        g.addEdge(4, 0);
        g.addEdge(4, 5);

        LinkedList<Integer> expected = new LinkedList<Integer>();
        expected.add(0);
        expected.add(2);
        expected.add(1);
        expected.add(3);
        expected.add(4);
        expected.add(5);

        Assert.assertEquals(expected,g.BFS(0));
    }

}