package edu.neu.coe.info6205.graphs.gis;

import edu.neu.coe.info6205.graphs.dag.DiGraph;
import edu.neu.coe.info6205.graphs.dag.Edge;

import java.util.*;

public class ShortestPaths<V, E extends Number> {
    public ShortestPaths(DiGraph<V, E> graph, V start) {
        this.graph = graph;
        this.start = start;
        this.table = dijkstra();
    }

    public double cost(V v) {
        Vertex vertex = table.getOrDefault(v, new Vertex(v));
        return vertex.cost;
    }

    public boolean hasPathTo(V v) {
        return table.containsKey(v);
    }

    public Iterable<Edge<V, E>> pathTo(V target) {
        Stack<Edge<V, E>> edges = new Stack<>();
        if (hasPathTo(target)) {
            V v = target;
            for(Vertex vertex = table.get(v); vertex.edgeTo!=null; ) {
                Edge<V, E> edgeTo = vertex.edgeTo;
                if (edgeTo.getTo()!=v) throw new RuntimeException("assertion error");
                edges.push(edgeTo);
                v = edgeTo.getFrom();
            }
        }
        return edges;
    }

    @Override
    public String toString() {
        return "ShortestPaths{" +
                "table=" + table +
                '}';
    }

    private Map<V, Vertex> dijkstra() {
        Map<V, Vertex> result = new HashMap<>();
        PriorityQueue<V> pq = new PriorityQueue<>();
        pq.offer(start);
        result.put(start, new Vertex(start, 0, null));
        while(!pq.isEmpty()) relax(graph, pq.poll(), result, pq);
        return result;
    }

    private void relax(DiGraph<V, E> graph, V vertex, Map<V, Vertex> table, PriorityQueue<V> pq) {
        for (Edge<V, E> e : graph.adjacent(vertex)) {
            V w = e.getTo();
            Vertex vertexW = table.getOrDefault(w, new Vertex(w));
            table.put(w, vertexW);
            double relaxedCost = table.get(e.getFrom()).cost + e.getAttributes().doubleValue();
            if (vertexW.cost > relaxedCost) {
                vertexW.relax(relaxedCost, e);
                if (pq.contains(w)) pq.remove(w);
                pq.offer(w);
            }
        }
    }

    private final DiGraph<V, E> graph;
    private final V start;
    private final Map<V, Vertex> table;

    class Vertex implements Comparable<Vertex> {
        @Override
        public int compareTo(Vertex o) {
            return Double.compare(cost, o.cost);
        }

        void relax(double cost, Edge<V, E> edgeTo) {
//            System.out.println("relaxing entry for vertex"+this+" to cost "+cost+" with edgeTo: "+edgeTo);
            this.cost = cost;
            this.edgeTo = edgeTo;
        }

        public Vertex(V vertex, double cost, Edge<V, E> edgeTo) {
            this.vertex = vertex;
            this.cost = cost;
            this.edgeTo = edgeTo;
        }

        public Vertex(V vertex) {
            this(vertex, Double.POSITIVE_INFINITY, null);
        }

        @Override
        public String toString() {
            return "Vertex {" + vertex +
                    ": cost=" + cost +
                    ", edgeTo=" + edgeTo +
                    '}';
        }

        private final V vertex;
        private double cost;
        private Edge<V, E> edgeTo;
    }
}
