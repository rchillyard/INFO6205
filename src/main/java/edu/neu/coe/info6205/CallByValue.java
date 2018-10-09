/*
 * Copyright (c) 2018. Phasmid Software
 */

package edu.neu.coe.info6205;

public class CallByValue {
    private int number = 0;
    private final int[] array = {0};

    // Hide "number" field
    public int incrementNumber1(int number) {
        number++;
        return number;
    }

    // Access "number" field
    public int incrementNumber2() {
        number++;
        return number;
    }

    // Also compare foreach loop and for loop
    public int[] incrementArray1(int[] array) {
        for (int a : array) {
            //noinspection UnusedAssignment
            a++; // CONSIDER what is going on here?
        }
        return array;
    }

    public int[] incrementArray2(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i]++;
        }
        return array;
    }

    public int[] incrementArray3() {
        for (int a : array) {
            //noinspection UnusedAssignment
            a++; // CONSIDER what is going on here?
        }
        return array;
    }

    public int[] incrementArray4() {
        for (int i = 0; i < array.length; i++) {
            array[i]++;
        }
        return array;
    }

    public static void main(String[] args) {
        CallByValue cbv = new CallByValue();

        cbv.incrementNumber1(cbv.number);
        System.out.println(cbv.number);

        cbv.incrementNumber2();
        System.out.println(cbv.number);

        cbv.incrementArray1(cbv.array);
        System.out.println(cbv.array[0]);

        cbv.incrementArray2(cbv.array);
        System.out.println(cbv.array[0]);

        cbv.incrementArray3();
        System.out.println(cbv.array[0]);

        cbv.incrementArray4();
        System.out.println(cbv.array[0]);
    }
}

