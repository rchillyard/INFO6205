package edu.neu.coe.info6205.union_find;

public interface UF {
    /**
     * Returns true if site p is connected to site q.
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @return {@code true} if there is a connection (direct or indirect) between p and q
     * @throws IllegalArgumentException if either p or q is an invalid site identifier
     */
    boolean connected(int p, int q);

    /**
     * Connects site p with site q
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @throws IllegalArgumentException if either p or q is an invalid site identifier
     */
    void union(int p, int q);
}
