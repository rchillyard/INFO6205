package edu.neu.coe.info6205.union_find;

import java.util.Random;

public class UnionFindClient {
    public static int count(int n){ //Packaging the code as a static method count with n as argument
        int connection_count = 0;
        UF_HWQUPC quick_union = new UF_HWQUPC(n);
        Random random = new Random(); //To help generate a random pair of integers between 0 and n-1
        while(quick_union.components()>1){ //To loop until all the sites are connected
            int num1 = random.nextInt(n);
            int num2 = random.nextInt(n);
            quick_union.connect(num1, num2);
            connection_count++;
        }
        return connection_count;
    }

    public static void main(String[] args){ //Main that calls count() and prints the returned value. Fixed set of n values are used here
        for(int i=1000; i<=10000; i=i+500){
            System.out.println("Number of connections generated for " + i + " sites are " + count(i));
        }
    }
}
