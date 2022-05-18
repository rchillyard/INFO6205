package edu.neu.coe.info6205;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import static edu.neu.coe.info6205.TailCalls.call;

public class TailCallTest {

    @Test
    public void testTailCall0() throws Throwable {
        final Long result = Factorial.factorialTailRec(1, 0).invoke();
        assertEquals(Long.valueOf(1L), result);
    }

    @Test
    public void testTailCall1() throws Throwable {
        final Long result = Factorial.factorialTailRec(1, 1).invoke();
        assertEquals(Long.valueOf(1L), result);
    }

    @Test
    public void testTailCall2() throws Throwable {
        final Long result = Factorial.factorialTailRec(1, 2).invoke();
        assertEquals(Long.valueOf(2L), result);
    }

    @Test
    public void testTailCall3() throws Throwable {
        final Long result = Factorial.factorialTailRec(1, 3).invoke();
        assertEquals(Long.valueOf(6L), result);
    }

    @Test
    public void testTailCall20() throws Throwable {
        final Long result = Factorial.factorialTailRec(1, 20).invoke();
        assertEquals(Long.valueOf(2432902008176640000L), result);
    }

    static class Factorial {
        public static TailCall<Long> factorialTailRec(final long factorial, final long number) {
            if (number <= 1)
                return TailCalls.done(factorial);
            else
                return call(() -> factorialTailRec(factorial * number, number - 1));
        }
    }
}
