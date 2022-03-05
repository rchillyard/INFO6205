package edu.neu.coe.info6205.union_find;

import java.util.Random;

public class UnionFind {
    static int connectionCount = 0;

    public static int getRandomNumber(int n){
        Random random = new Random();
        return random.nextInt(n);
    }

    public static void count(){
        connectionCount++;
    }

    public static void main(String[] args) {

        int[] exponentialIncreaseInNumbers = {1000, 2000, 4000, 8000, 16000, 32000, 64000};

        for (int number : exponentialIncreaseInNumbers) {

            connectionCount = 0;

            UF_HWQUPC uf_hwqupc = new UF_HWQUPC(number);

            while (uf_hwqupc.components() != 1) {

// Generating two completely random number
                int number1 = getRandomNumber(number);
                int number2 = getRandomNumber(number);

                count();

// If they are already equal and they have already connected
                if (number1 == number2 || uf_hwqupc.connected(number1, number2)) {
                    continue;
                } else {
                    uf_hwqupc.union(number1, number2);
                }
            }
            System.out.println("Sites: " + number + " Connections Count: " + connectionCount);
        }

    }


}
