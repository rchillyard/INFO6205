package edu.neu.coe.info6205;

import java.util.Arrays;
import java.util.Comparator;

public class VLA2 {
    public VLA2(int d) {
        dishSize = d;
    }

    public static void main(String[] args) {
        Comparator<VLA2> cf = Comparator.comparingInt(o -> o.dishSize);
        VLA2[] va = {new VLA2(40), new VLA2(200), new VLA2(60)};
        Arrays.sort(va, cf);
        int index = Arrays.binarySearch(va, new VLA2(40), cf);
        System.out.print(index + " ");
        index = Arrays.binarySearch(va, new VLA2(80), cf);
        System.out.print(index >= 0);
    }

    private final int dishSize;
}