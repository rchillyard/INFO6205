package edu.neu.coe.info6205.union_find;

/**
 * This interface models a situation where a set of objects ("sites") maintains a set of connections between the objects.
 * There are two operations: connect and isConnected.
 * Object (sites) are identified by unique integers.
 */
public interface Connections {
    /**
     * Returns true if site p is connected to site q.
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @return {@code true} if there is a connection (direct or indirect) between p and q
     * @throws IllegalArgumentException if either p or q is an invalid site identifier
     */
    boolean isConnected(int p, int q);

    /**
     * Connects site p with site q
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @throws IllegalArgumentException if either p or q is an invalid site identifier
     */
    void connect(int p, int q);
}