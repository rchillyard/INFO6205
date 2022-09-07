package edu.neu.coe.info6205.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class StopwatchTest {

    @Test
    public void lap1() {
        final Random random = new Random();
        @SuppressWarnings("MismatchedReadAndWriteOfArray") int[] xs = new int[10000];
        for (int i = 0; i < xs.length; i++) xs[i] = random.nextInt();
        try (Stopwatch target = new Stopwatch()) {
            Arrays.sort(xs);
            System.out.println(target.lap());
        }
    }

    @Test(expected = Throwable.class)
    public void lap2() {
        try (Stopwatch target = new Stopwatch()) {
            target.close();
            Thread.sleep(10);
            System.out.println(target.lap());
        } catch (InterruptedException ignored) {
        }
    }

    @Test
    public void close() {
        try {
            Stopwatch target = new Stopwatch();
            target.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}