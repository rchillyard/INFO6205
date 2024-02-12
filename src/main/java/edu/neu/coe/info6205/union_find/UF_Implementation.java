package edu.neu.coe.info6205.union_find;

import java.util.ArrayList;
import java.util.Random;

public class UF_Implementation {

    public static int count(int n) {
        int connection = 0;
        Random random = new Random();
        UF_HWQUPC uf_hwqupc = new UF_HWQUPC(n);
        int loopTime = n;

        while (loopTime > 1) {
            int p = random.nextInt(n);
            int q = random.nextInt(n);
            connection++;
            if (!uf_hwqupc.connected(p, q)){
                uf_hwqupc.union(p, q);
                loopTime--;
            }
        }
        return connection;
    }
}

class Run {
    public static void main(String[] args) {
        //System.out.println(UF_Implementation.count(2));
        int count50 = 0;
        int count100 = 0;
        int count200 = 0;
        int count400 = 0;
        int count800 = 0;
        int count1600 = 0;
        for (int i = 0; i < 10; i++) {
            ArrayList<Integer> res = new ArrayList<>();
            res.add(UF_Implementation.count(50));
            count50 += UF_Implementation.count(50);
            res.add(UF_Implementation.count(100));
            count100 += UF_Implementation.count(100);
            res.add(UF_Implementation.count(200));
            count200 += UF_Implementation.count(200);
            res.add(UF_Implementation.count(400));
            count400 += UF_Implementation.count(400);
            res.add(UF_Implementation.count(800));
            count800 += UF_Implementation.count(800);
            res.add(UF_Implementation.count(1600));
            count1600 += UF_Implementation.count(1600);
            //System.out.println(res.get(0) + " " + res.get(1) + " "+ res.get(2) + " " + res.get(3) + " " + res.get(4) + " "+ res.get(5));
        }
        //System.out.println("------------");
        System.out.println("Length = 50, number of pairs generated for the UF: " + count50 / 10);
        System.out.println("Length = 100, number of pairs generated for the UF: " + count100 / 10);
        System.out.println("Length = 200, number of pairs generated for the UF: " + count200 / 10);
        System.out.println("Length = 400, number of pairs generated for the UF: " + count400 / 10);
        System.out.println("Length = 800, number of pairs generated for the UF:  " + count800 / 10);
        System.out.println("Length = 1600, number of pairs generated for the UF:   " + count1600 / 10);

    }
}


