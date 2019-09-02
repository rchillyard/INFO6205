/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.sort.simple;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("ALL")
public class InsertionSortTest {

    @Test
    public void sort() throws Exception {
        final List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.add(2);
        list.add(1);
        Integer[] xs = list.toArray(new Integer[0]);
        Helper<Integer> helper = new Helper<>("InsertionSort", xs.length);
        InsertionSort<Integer> sorter = new InsertionSort<Integer>(helper);
        Integer[] ys = sorter.sort(xs);
        assertTrue(helper.sorted(ys));
        System.out.println(sorter.toString());
    }

    @Test
    public void sortQuiz6() throws Exception {
        assertEquals("epTy",testWord("Type"));
        assertEquals("aABb",testWord("BabA"));
//        assertEquals("Be?y",testWord("By?e")); answer is By?e
    }

    private String testWord(String word) {
        final char[] charsX = word.toCharArray();
        final Character[] chars = new Character[charsX.length];
        for (int i=0; i<chars.length; i++) chars[i] = charsX[i];
        Helper<Character> helper = new Quiz6Helper("InsertionSort", chars.length);
        InsertionSort<Character> sorter = new InsertionSort<>(helper);
        Character[] sorted = sorter.sort(chars);
        final StringBuilder result = new StringBuilder();
        for (Character x: sorted) result.append(x);
        return result.toString();
    }

    class Quiz6Helper extends Helper<Character> {

        public Quiz6Helper(String description, int n) {
            super(description, n);
        }

        @Override
        boolean less(Character v, Character w) {
            if (Character.isAlphabetic(v) && Character.isAlphabetic(w))
                return super.less(Character.toLowerCase(v), Character.toLowerCase(w));
            else return false;
        }
    }
}