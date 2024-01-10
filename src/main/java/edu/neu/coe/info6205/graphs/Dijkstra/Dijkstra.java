/**
 *  The {@code Dijkstra} class represents a data type for solving the
 *  single-source shortest paths problem in edge-weighted digraphs
 *  where the edge weights are non-negative.
 *  <p>
 *  This implementation uses <em>Dijkstra's algorithm</em> with a
 *  <em>binary heap</em>. The constructor takes
 *  &Theta;(<em>E</em> log <em>V</em>) time in the worst case,
 *  where <em>V</em> is the number of vertices and <em>E</em> is
 *  the number of edges. Each instance method takes &Theta;(1) time.
 *  It uses &Theta;(<em>V</em>) extra space (not including the
 *  edge-weighted digraph).
 *  <p>
 *  This correctly computes shortest paths if all arithmetic performed is
 *  without floating-point rounding error or arithmetic overflow.
 *  This is the case if all edge weights are integers and if none of the
 *  intermediate results exceeds 2<sup>52</sup>. Since all intermediate
 *  results are sums of edge weights, they are bounded by <em>V C</em>,
 *  where <em>V</em> is the number of vertices and <em>C</em> is the maximum
 *  weight of any edge.
 *  <p>
 *  For additional documentation,    
 *  see <a href="https://algs4.cs.princeton.edu/44sp">Section 4.4</a> of    
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne. 
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */

package edu.neu.coe.info6205.graphs.Dijkstra;

import java.util.Stack;
import java.util.function.BiConsumer;

public class Dijkstra {

    /**
     * Method to solve Dijkstra's Shortest Paths algorithm for the starting point s.
     *
     * @param s the starting point.
     */
    public ShortestPaths shortestPaths(int s) {
        ShortestPaths result = new ShortestPaths();
        result.solve(s);
        return result;
    }

    public Dijkstra(EdgeWeightedDigraph G) {
        this.G = G;
        n = G.V();
        for (DirectedEdge e : G.edges())
            if (e.weight() < 0)
                throw new IllegalArgumentException("edge " + e + " has negative weight");
    }

    public class ShortestPaths {

        public double distTo(int v) {
            validateVertex(v);
            return distTo[v];
        }

        public boolean hasPathTo(int v) {
            validateVertex(v);
            return distTo[v] < Double.POSITIVE_INFINITY;
        }

        public Iterable<DirectedEdge> pathTo(int v) {
            validateVertex(v);
            if (!hasPathTo(v)) return null;
            Stack<DirectedEdge> path = new Stack<>();
            for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) path.push(e);
            return path;
        }

        void solve(int s) {
            for (int v = 0; v < n; v++)
                distTo[v] = Double.POSITIVE_INFINITY;
            distTo[s] = 0.0;
            validateVertex(s);
            pq.insert(s, distTo[s]);
            while (!pq.isEmpty()) {
                int v = pq.delMin();
                for (DirectedEdge e : G.adj(v))
                    relax(e);
            }
            assert check(G, s);
        }

        ShortestPaths() {
            pq = new IndexMinPQ<>(n);
            distTo = new double[n];
            edgeTo = new DirectedEdge[n];
            keyDecreaser = pq::decreaseKey;
            keyInserter = pq::insert;
        }

        private void relax(DirectedEdge e) {
            int v = e.from(), w = e.to();
            double weight = e.weight();
            final double eDistance = distTo[v] + weight;
            if (distTo[w] > eDistance) {
                distTo[w] = eDistance;
                edgeTo[w] = e;
                (pq.contains(w) ? keyDecreaser : keyInserter).accept(w, distTo[w]);
            }
        }

        private boolean check(EdgeWeightedDigraph G, int s) {
            for (DirectedEdge e : G.edges()) {
                if (e.weight() < 0) {
                    System.err.println("negative edge weight detected");
                    return false;
                }
            }
            if (distTo[s] != 0.0 || edgeTo[s] != null) {
                System.err.println("distTo[s] and edgeTo[s] inconsistent");
                return false;
            }
            for (int v = 0; v < G.V(); v++) {
                if (v == s) continue;
                if (edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY) {
                    System.err.println("distTo[] and edgeTo[] inconsistent");
                    return false;
                }
            }

            for (int v = 0; v < G.V(); v++) {
                for (DirectedEdge e : G.adj(v)) {
                    int w = e.to();
                    if (distTo[v] + e.weight() < distTo[w]) {
                        System.err.println("edge " + e + " not relaxed");
                        return false;
                    }
                }
            }

            for (int w = 0; w < G.V(); w++) {
                if (edgeTo[w] == null) continue;
                DirectedEdge e = edgeTo[w];
                int v = e.from();
                if (w != e.to()) return false;
                if (distTo[v] + e.weight() != distTo[w]) {
                    System.err.println("edge " + e + " on shortest path not tight");
                    return false;
                }
            }
            return true;
        }

        private void validateVertex(int v) {
            int V = distTo.length;
            if (v < 0 || v >= V)
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
        }

        private final BiConsumer<Integer, Double> keyDecreaser;
        private final BiConsumer<Integer, Double> keyInserter;
        private final double[] distTo;          // distTo[v] = distance  of shortest s->v path
        private final DirectedEdge[] edgeTo;    // edgeTo[v] = last edge on shortest s->v path
        private final IndexMinPQ<Double> pq;    // priority queue of vertices
    }

    private final EdgeWeightedDigraph G;    // G is the original graph passed in to the constructor.

    private final int n;
}