package edu.neu.coe.info6205.questions;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Result {

    /**
     * Method to determine the number of integers in the given list which are repeated.
     * Note that the number of repetitions isn't significant, as long as it's at least one.
     *
     * @param numbers a List of Integer.
     * @return the number of integers which are repeated.
     */
    public static int countDuplicates(List<Integer> numbers) {
        Collections.sort(numbers);
        int duplicates = 0;
        int previous = Integer.MIN_VALUE;
        boolean noted = false;
        for (Integer number : numbers) {
            if (number == previous) {
                if (!noted) duplicates++;
                noted = true;
            }
            else {
                previous = number;
                noted = false;
            }
        }
        return duplicates;
    }

    public static void main(String[] args) {
        Integer[] array = {3, 4, 1, 2, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(array));
        List<Integer> ints = Arrays.asList(array);
        int duplicate = countDuplicates(ints);
        System.out.println(duplicate);
    }

}


