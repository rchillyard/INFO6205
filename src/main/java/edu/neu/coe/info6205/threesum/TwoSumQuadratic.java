package edu.neu.coe.info6205.threesum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of ThreeSum which follows the brute-force approach of
 * testing every candidate in the solution-space.
 * The array provided in the constructor may be randomly ordered.
 * <p>
 * This algorithm runs in O(N^3) time.
 */
class TwoSumQuadratic implements TwoSum {
    /**
     * Construct a ThreeSumCubic on a.
     * @param a an array.
     */
    public TwoSumQuadratic(int[] a) {
        this.a = a;
        length = a.length;
    }

    public Pair[] getPairs() {
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < length; i++)
            for (int j = i + 1; j < length; j++) {
                    if (a[i] + a[j] == 0)
                        pairs.add(new Pair(a[i], a[j]));
            }
        Collections.sort(pairs);
        return pairs.stream().distinct().toArray(Pair[]::new);
    }

    private final int[] a;
    private final int length;
}