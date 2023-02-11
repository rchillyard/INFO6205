package edu.neu.coe.info6205.union_find;

import edu.neu.coe.info6205.graphs.BFS_and_prims.StdRandom;

import java.util.Scanner;

public class UF_Client {
    public static int count(int n){
        UF_HWQUPC ufObj = new UF_HWQUPC(n);
        int m = 0;
        while(ufObj.components()>=2){
            int a1= StdRandom.uniform(n),a2=StdRandom.uniform(n); //Generating random numbers pairs
            if(!ufObj.isConnected(a1,a2)){//checking if the sites are connected
                ufObj.union(a1,a2);//performing union operation on sites if they are not already connected
            }
            m++;
        }
        return m;
    }

    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        System.out.print("Input the Initial Number of Sites(n): ");
        int sites=scan.nextInt();
        int exp=30;//number of trails to be performed
        int n=sites;
        for(int obs=0;obs<15;obs++){
            double total=0;
            for (int j=0;j<exp;j++){
                total+=count(n);
            }
            System.out.println("No of objects (n): " + n + ", No of pairs (m) :" + total/exp);
            n = 2*n;
        }
    }
}
