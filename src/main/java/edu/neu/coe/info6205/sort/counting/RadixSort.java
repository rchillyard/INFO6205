package edu.neu.coe.info6205.sort.counting;


/**
 * Radix Sort
 * Radix sort is an integer sorting algorithm that sorts data with integer keys
 * by grouping the keys by individual digits that share the same significant
 * position and value (place value). Radix sort uses counting sort as a
 * subroutine to sort an array of numbers.
 * <p>
 * <b>Disclaimer:</b> This radix sort can only sort positive integers
 *
 * @version 1.0
 * @since 13th May 2020
 */

public class RadixSort {

    /**
     * findMaxInt method is used to find maximum number in the array
     * within the provided range i.e from and to
     *
     * @param numArr It contains an array of numbers from which maximum value needs to be obtained
     * @param from   This is the starting index from which scanning for maximum number will begin
     * @param to     This is the ending index until which scanning for maximum number will be continued
     * @return int This method returns maximum number between from index and to index
     */
    public int findMaxInt(int[] numArr, int from, int to) {
        int maxVal = numArr[from];
        for (int i = from; i <= to; i++)
            maxVal = Math.max(maxVal, numArr[i]);
        return maxVal;
    }

    /**
     * countSort method is implementation of basic counting sort algorithm.
     * We provide exponent i.e unit's digit, ten's digit or hundred's digit etc on which counting sort needs to be performed
     *
     * @param numArr It contains an array of numbers on which counting sort needs to be performed
     * @param exp    This is the exponent input on which counting sort would be performed e.g 1, 10, 100, 1000 etc.
     * @param from   This is the starting index from which sorting operation will begin
     * @param to     This is the ending index until which sorting operation will be continued
     */
    public void countSort(int[] numArr, int exp, int from, int to) {
        int arrLength = numArr.length;
        int[] result = new int[arrLength]; //This stores output result
        int[] count = new int[10]; // This maintains digit wise occurrence count

        //This method records occurrence of digits in count[]
        for (int i = from; i <= to; i++)
            count[(numArr[i] / exp) % 10]++;

        // Modifying value of count[i] so that it now contains actual position of the digit
        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Building result array to contain radix sorted output array on selected exponent
        for (int i = to; i >= from; i--) {
            result[count[(numArr[i] / exp) % 10] - 1 + from] = numArr[i];
            count[(numArr[i] / exp) % 10]--;  // Reducing count[] to adjust the next location of particular digit
        }

        //Copying result array in original array
        if (to + 1 - from >= 0) System.arraycopy(result, from, numArr, from, to + 1 - from);
    }

    /**
     * sort method is implementation of radix sort algorithm.
     *
     * @param numArr It contains an array of numbers on which radix sort needs to be performed
     * @param from   This is the starting index from which sorting operation will begin
     * @param to     This is the ending index until which sorting operation will be continued
     */
    public void sort(int[] numArr, int from, int to) throws Exception {

        // Sort Validations on input

        if (numArr == null || numArr.length == 1 || from == to) return;

        if (from > to) throw new Exception("From value should be less than to");

        if (from < 0 || (from > numArr.length - 1))
            throw new ArrayIndexOutOfBoundsException("From should be between 0 and " + (numArr.length - 1));

        if (to > numArr.length - 1)
            throw new ArrayIndexOutOfBoundsException("To should be between 0 and " + (numArr.length - 1));

        // Finding max number
        int maxVal = findMaxInt(numArr, from, to);
        //Performing counting sort on every exponent
        int exp = 1;
        while (maxVal / exp > 0) {
            countSort(numArr, exp, from, to);
            exp *= 10;
        }
    }

}