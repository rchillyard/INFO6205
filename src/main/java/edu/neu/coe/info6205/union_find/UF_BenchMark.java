package edu.neu.coe.info6205.union_find;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Random;

public class UF_BenchMark {

    private static long edgesToMakeSingleComponent = 0;

    public static void main(String[] args) {
        for (int i = 500; i < 10000000; i = i*2) {
            repeatExpMTimes(i, 500);
        }
    }

    public static void repeatExpMTimes(int n, int m) {
        long averageUnionCount = 0, averagePairCount = 0;

        BigDecimal constVal = BigDecimal.valueOf(n);
        constVal = constVal.pow(2);
        constVal = constVal.subtract(BigDecimal.valueOf(n));
        constVal = constVal.divide(BigDecimal.valueOf(n).pow(2), new MathContext(20));
        BigDecimal finalVal = BigDecimal.ONE;

        //System.out.println("Const value: "+);

        for (int i = 0; i < n - 1; i++) {

            BigDecimal temp = constVal.subtract(BigDecimal.valueOf(i).divide(BigDecimal.valueOf(n).pow(2), new MathContext(20)));
            finalVal = finalVal.multiply(temp);
        }


        long max = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            edgesToMakeSingleComponent = 0;
            long temp = doExperimentForN(n);
            if (temp > max) {
                max = temp;
            }
            averageUnionCount += edgesToMakeSingleComponent;
            averagePairCount += temp;
        }
        System.out.println("N: "+n+", pairs generated average: "+(averagePairCount/m));
        System.out.println("N: "+n+", number of times union is called average: "+(averageUnionCount/m));
        /*System.out.println("Final Value: probability: "+finalVal.round(new MathContext(20)));
        System.out.println("Final Value for n: "+n+", value : "+
                finalVal.multiply(BigDecimal.valueOf(n).pow(2)).round(new MathContext(20)));
        System.out.println("Max count = "+max);
         */

    }

    public static long doExperimentForN(int n) {
        long count = 0;
        UF_HWQUPC unionFind = new UF_HWQUPC(n);
        //unionFind.c
        Random random = new Random();
        try {
            while(unionFind.components() != 1) {
                int i = random.nextInt(n);
                int j = random.nextInt(n);
                if (!unionFind.connected(i, j)) {
                    unionFind.union(i, j);
                    edgesToMakeSingleComponent++;
                }
                count++;
            }
        } catch (Exception e) {
            System.out.println("exception for N: "+n+", count: "+count+", Exception = "+e);
        }

        return count;
    }

}
