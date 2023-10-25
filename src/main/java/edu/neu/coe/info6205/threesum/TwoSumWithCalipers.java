package edu.neu.coe.info6205.threesum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * Implementation of ThreeSum which follows the approach of dividing the solution-space into
 * N sub-spaces where each sub-space corresponds to a fixed value for the middle index of the three values.
 * Each sub-space is then solved by expanding the scope of the other two indices outwards from the starting point.
 * Since each sub-space can be solved in O(N) time, the overall complexity is O(N^2).
 * <p>
 * The array provided in the constructor MUST be ordered.
 */
public class TwoSumWithCalipers implements TwoSum {
    /**
     * Construct a ThreeSumQuadratic on a.
     *
     * @param a a sorted array.
     */
    public TwoSumWithCalipers(int[] a) {
        this.a = a;
        length = a.length;
    }

    @Override
    public Pair[] getPairs() {
        List<Pair> pairs = new ArrayList<>();
        Collections.sort(pairs);
        pairs.addAll(calipers(a, (t) -> t.sum()));
        return pairs.stream().distinct().toArray(Pair[]::new);
    }

    /**
     * Get a list of Pairs .
     *
     * @param a        a sorted array of ints.
     * @param function a function which takes a triple and returns the comparison of sum of the triple with zero.
     * @return a Triple such that
     */
    public static List<Pair> calipers(int[] a, Function<Pair, Integer> function) {
        List<Pair> pairs = new ArrayList<>();
        // FIXME : implement getPairs
        for(int i=0;i<a.length-2;i++){
            for(int j=i+1; j<a.length;j++){
                // if(i==0 || (i>0 && a[i] != a[i-1])){
                if(function.apply(new Pair(a[i],a[j]))==0){
                    pairs.add(new Pair(a[i],a[j]));
                }
                //  }
            }
        }
        // END 
        return pairs;
    }

    private final int[] a;
    private final int length;
}
