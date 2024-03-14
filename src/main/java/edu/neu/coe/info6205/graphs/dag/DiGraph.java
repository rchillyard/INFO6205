package edu.neu.coe.info6205.graphs.dag;

import edu.neu.coe.info6205.SizedIterable;
import edu.neu.coe.info6205.bqs.Bag;
import edu.neu.coe.info6205.bqs.Bag_Array;
import edu.neu.coe.info6205.bqs.Stack;
import edu.neu.coe.info6205.bqs.Stack_LinkedList;
import edu.neu.coe.info6205.graphs.undirected.AbstractGraph;

import java.util.*;
import java.util.function.Consumer;

public class DiGraph<V, E> extends AbstractGraph<V, Edge<V, E>> {

    /**
     * Reverse the sense of this DAG.
     *
     * @return a DAG whose edges all point in the opposite direction to those in this DAG.
     */
    public DiGraph<V, E> reverse() {
        DiGraph<V, E> result = new DiGraph<>();
        for (Edge<V, E> e : edges()) result.addEdge(e.reverse());
        return result;
    }

    public void addEdge(Edge<V, E> edge) {
        getAdjacencyBag(edge.getFrom()).add(edge);
        getAdjacencyBag(edge.getTo());
    }

    public SizedIterable<Edge<V, E>> edges() {
        Bag<Edge<V, E>> result = new Bag_Array<>();
        for (Iterable<Edge<V, E>> b : adjacentEdges.values())
            for (Edge<V, E> e : b)
                result.add(e);
        return result;
    }

    protected Stack<V> reversePostOrderDFS() {
        Stack<V> postOrderStack = new Stack_LinkedList<>();
        new DepthFirstSearch(new TreeSet<>(), null, postOrderStack::push).innerDfs();
        return postOrderStack;
    }

    public DAG<Kernel<V>, E> kernelDAG() {
        final DAG_Impl<Kernel<V>, E> result = new DAG_Impl<>(new Random(0L));
        final TreeSet<V> marked = new TreeSet<>();
        for (V vertex : reverse().reversePostOrderDFS()) {
            Kernel<V> kernel = new Kernel<>();
            new DepthFirstSearch(marked, kernel::add, null).innerDfs(vertex);
            if (!kernel.vertices.isEmpty())
                result.addVertex(kernel);
        }
        final List<Kernel<V>> kernels = result.vertices().toList();
        for (Edge<V, E> edge : edges()) {
            final Kernel<V> from = kernels.stream().filter((k) -> k.vertices.contains(edge.getFrom())).findAny().orElse(null);
            final Kernel<V> to = kernels.stream().filter((k) -> k.vertices.contains(edge.getTo())).findAny().orElse(null);
            if (from != null && to != null && from != to)
                result.addEdge(new Edge<>(from, to, edge.getAttributes()));
        }
        return result;
    }

    @Override
    public String toString() {
        return adjacentEdges.toString();
    }

    class DepthFirstSearch {

        public DepthFirstSearch(TreeSet<V> marked, Consumer<V> pre, Consumer<V> post) {
            this.pre = pre;
            this.post = post;
            this.marked = marked;
            if (pre == null && post == null)
                throw new RuntimeException("DepthFirstSearch: pre and post cannot both be null");
        }

        void innerDfs() {
            for (V v : vertices()) innerDfs(v);
        }

        void innerDfs(V v) {
            // TODO create a HashMap of V and Boolean
            if (marked.contains(v)) return;
            marked.add(v);
            if (pre != null) pre.accept(v);
            for (Edge<V, E> e : adjacentEdges.get(v)) {
                V v1 = e.getTo();
                if (!marked.contains(v1)) innerDfs(v1);
            }
            if (post != null) post.accept(v);
        }

        private final TreeSet<V> marked;
        private final Consumer<V> pre;
        private final Consumer<V> post;
    }

    static class Kernel<T> {
        private final Collection<T> vertices;

        public Kernel(Collection<T> vertices) {
            this.vertices = vertices;
        }

        public Kernel() {
            this(new ArrayList<>());
        }

        public void add(T t) {
            vertices.add(t);
        }

        @Override
        public String toString() {
            return vertices.toString();
        }
    }
}