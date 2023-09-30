package edu.neu.coe.info6205;

import org.junit.Test;

import java.math.BigInteger;

import static edu.neu.coe.info6205.TailCalls.call;
import static org.junit.Assert.assertEquals;

public class TailCallTest {

    @Test
    public void testTailCall0() throws Throwable {
        final Integer result = Factorial.factorialTailRec(1, 0).invoke();
        assertEquals(Integer.valueOf(1), result);
    }

    @Test
    public void testTailCall1() throws Throwable {
        final Integer result = Factorial.factorialTailRec(1, 1).invoke();
        assertEquals(Integer.valueOf(1), result);
    }

    @Test
    public void testTailCall2() throws Throwable {
        final Long result = Factorial.factorialTailRec(1L, 2).invoke();
        assertEquals(Long.valueOf(2L), result);
    }

    @Test
    public void testTailCall3() throws Throwable {
        final Long result = Factorial.factorialTailRec(1L, 3).invoke();
        assertEquals(Long.valueOf(6L), result);
    }

    @Test
    public void testTailCall20() throws Throwable {
        final Long result = Factorial.factorialTailRec(1L, 20).invoke();
        assertEquals(Long.valueOf(2432902008176640000L), result);
    }

    @Test
    public void testTailCall1000() throws Throwable {
        final int number = 1000;
        final BigInteger result = Factorial.factorialTailRec(BigInteger.valueOf(1L), number).invoke();
        assertEquals(8530, result.bitLength());
    }

    static class Factorial {
        public static <T extends Number> TailCall<T> factorialTailRec(final T factorial, final int number) {
            if (number <= 1)
                return TailCalls.done(factorial);
            else
                return call(() -> factorialTailRec(scale(factorial, number), number - 1));
        }

        public static <T extends Number> T scale(final T t, final int scale) {
            Object result = t.getClass().isAssignableFrom(BigInteger.class) ? ((BigInteger) t).multiply(BigInteger.valueOf(scale)) :
                    (t.getClass().isAssignableFrom(Long.class)) ? (t.longValue() * scale) :
                            (t.getClass().isAssignableFrom(Integer.class)) ? (t.intValue() * scale) :
                                    null;
            if (result != null)
                //noinspection unchecked
                return (T) result;
            else throw new RuntimeException("unsupported type");
        }
    }
}