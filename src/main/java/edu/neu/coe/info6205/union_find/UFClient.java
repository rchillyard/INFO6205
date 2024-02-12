package edu.neu.coe.info6205.union_find;

import java.util.Random;
public class UFClient {

    public static int count(int n) {
        UF_HWQUPC uf_hwqupc = new UF_HWQUPC(n);
        Random r = new Random();
        int connections = 0;

        while (uf_hwqupc.components() != 1) {
            int i = r.nextInt(n);
            int j = r.nextInt(n);

            if (!uf_hwqupc.connected(i, j)) {
                uf_hwqupc.union(i, j);
                connections++;
            }
        }
        return connections;
    }

    public static void main(String[] args) {
        for (int n = 250; n < 40000; n *= 2) {
            int connections = count(n);
            System.out.println("N = " + n + " Connections = " + connections);
        }
    }
}
