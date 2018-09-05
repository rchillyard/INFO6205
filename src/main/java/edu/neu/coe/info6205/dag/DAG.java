package edu.neu.coe.info6205.dag;

import java.util.function.Function;

public interface DAG<Vertex> {

    Iterable<Vertex> vertices();

    Iterable<Edge<Vertex>> adjacent(Vertex vertex);

    void dfs(Vertex vertex, Function<Vertex, Void> pre, Function<Vertex, Void> post);

    Iterable<Vertex> sorted();

    DAG<Vertex> reverse();

    int V();

    int E();
}
