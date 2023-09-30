/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.codelength;

/**
 * @author prospace
 */
public class CodeLength {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Solution s1 = new Solution();
//        int n = 3;
//        int length = 1;
//        System.out.println("The ans is " + s1.work1(n, length));
        for (int n = 0; n < 10; n++)
            for (int i = 0; i < 10; i++)
                System.out.println("The ans for n=" + n +
                        ", i=" + i +
                        " is " + s1.work1(n, i));
    }

}