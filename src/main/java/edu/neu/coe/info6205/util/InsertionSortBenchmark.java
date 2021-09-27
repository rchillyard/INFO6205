package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.sort.elementary.InsertionSort;
import org.ini4j.Ini;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class InsertionSortBenchmark {

    public static int N = 100;
    static Ini ini = new Ini();
    static Config config = new Config(ini);

    public static void main(String[] args) {
        System.out.println("Program for benchmarking Insertion Sort");
        String[] arrayOrdering = new String[]{"random", "ordered", "partially-ordered", "reverse-ordered"};

        for (String s : arrayOrdering) {
            for (int i = 0; i < 5; i++) {
                Integer[] array = new Integer[N];
                List<Integer> list = new ArrayList<>();
                Helper<Integer> helper = new BaseHelper<>("Insertion sort", N, config);
                SortWithHelper<Integer> arraySorter = new InsertionSort<>(helper);

                Benchmark<Integer[]> benchmark = new Benchmark_Timer<Integer[]>("Insertion Sort", b -> arraySorter.sort(b));

                if (s.equalsIgnoreCase("reverse-ordered")) {
                    array = initializeReverseOrderedArray(list);
                } else if (s.equalsIgnoreCase("ordered")) {
                    array = initializeOrderedArray(list);
                }else if (s.equalsIgnoreCase("partially-ordered")) {
                    array = initializePartialOrderedArray(list);
                }else{
                    array = generateRandomArray(helper);
                }
                double lapTime = benchmark.run(array, N);
                System.out.println("Laptime for "+s+" array when n is " + N + " is: " + lapTime);
                System.out.println("-------------------------------------------------------------");
                N = N * 2;
            }
            System.out.println("***************************************************************************");
            N = 100;
        }



    }

    public static Integer[] generateRandomArray(Helper<Integer> helper){
        return helper.random(Integer.class, r -> r.nextInt(1000));
    }


    public static Integer[] initializeOrderedArray( List<Integer> list) {
        int size = N;
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        Integer[] xs = list.toArray(new Integer[0]);

        return xs;
    }

        public static Integer[] initializeReverseOrderedArray(List<Integer> list){
            for (int i = 0; i < N; i++) {
                list.add(N - i);
            }
            Integer[] xs = list.toArray(new Integer[0]);
            return xs;
        }

        public static Integer[] initializePartialOrderedArray(List<Integer> list) {
            Random r = new Random();

            for (int a = 0; a < N; a++) {
                if ((a + 1) % 10 != 0) {
                    list.add((a + 1) * 10);
                } else {
                    list.add(r.nextInt(1000));
                }
            }
            Integer[] xs = list.toArray(new Integer[0]);
            return xs;
        }
    }

