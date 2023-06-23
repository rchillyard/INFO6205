package edu.neu.coe.info6205.madhava;

import com.phasmidsoftware.number.core.Rational;
import org.junit.Test;
import scala.util.Try;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.*;

public class BigNumberTest {
    // NOTE that this approximatePi is nevertheless an exact number. It's an exact number that is close to pi. But we know it is not pi.
    final BigNumber approximatePi = BigNumber.value(3, 1415927, true);

    @Test
    public void testBigNumberPrimary() {
        assertEquals(new BigNumber(BigInteger.ONE, new int[]{}, true), new BigNumber(BigInteger.ONE, new int[]{0, 0, 0}, true));
    }

    @Test
    public void testBigNumber() {
        assertEquals(new BigNumber(BigInteger.TEN, new int[]{}, false), new BigNumber(-10));
    }

    @Test
    public void testIntValue() {
        assertEquals(-1, BigNumber.value(-1).intValue());
        assertEquals(0, BigNumber.value(0).intValue());
        assertEquals(1, BigNumber.value(1).intValue());
    }

    @Test(expected = ArithmeticException.class)
    public void testIntValueBad1() {
        assertEquals(0, BigNumber.value(Long.MAX_VALUE).intValue());
    }

    @Test(expected = BigNumber.BigNumberException.class)
    public void testIntValueBad2() {
        assertEquals(0, BigNumber.pi.longValue());
    }


    @Test
    public void testLongValue() {
        assertEquals(0L, BigNumber.value(0).longValue());
    }

    @Test(expected = ArithmeticException.class)
    public void testLongValueBad1() {
        assertEquals(0L, BigNumber.value(Long.MAX_VALUE).add(BigNumber.one).longValue());
    }

    @Test(expected = BigNumber.BigNumberException.class)
    public void testLongValueBad2() {
        assertEquals(0L, BigNumber.pi.longValue());
    }

    @Test
    public void testDoubleValue() {
        assertEquals(-1, BigNumber.one.negate().doubleValue(), 1E15);
        assertEquals(0, BigNumber.zero.doubleValue(), 1E15);
        assertEquals(1, BigNumber.one.doubleValue(), 1E15);
        assertEquals(Math.PI, BigNumber.pi.doubleValue(), 1E15);
    }

    @Test
    public void testFloatValue() {
        assertEquals(0., BigNumber.value(0).floatValue(), 1E-5);
    }

    @Test
    public void testValue() {
        assertEquals(new BigNumber(BigInteger.ZERO, new int[]{}, true), BigNumber.value(0, 0, true));
        assertEquals(new BigNumber(BigInteger.valueOf(3), new int[]{1, 4, 1, 5, 9, 2, 7}, true), BigNumber.value(3, 1415927, true));
    }

    @Test
    public void testParse() {
        assertEquals(BigNumber.zero, BigNumber.parse("0"));
        assertEquals(new BigNumber(BigInteger.valueOf(3), new int[]{1, 4, 1, 5, 9, 2, 7}, true), BigNumber.parse("3.1415927"));
    }

    @Test
    public void testIsExact() {
        assertTrue(BigNumber.zero.isExact());
        assertTrue(BigNumber.parse("0").isExact());
        assertTrue(new BigNumber(BigInteger.valueOf(3), new int[]{1, 4, 1, 5, 9, 2, 7}, true).isExact());
        assertTrue(BigNumber.parse("3.1415927").isExact());
        assertFalse(BigNumber.one.divide(3).isExact());
        assertFalse(BigNumber.value(Rational.apply("1/3")).isExact());

    }

    @Test
    public void testAdd1() {
        BigNumber target = BigNumber.value(3, 1415927);
        BigNumber addend = BigNumber.value(1, 111111);
        assertEquals(new BigNumber(BigInteger.valueOf(4), new int[]{2, 5, 2, 7, 0, 3, 7}, true), target.add(addend));
    }

    @Test
    public void testAdd2() {
        BigNumber addend = BigNumber.value(1, 111111, false);
        assertEquals(new BigNumber(BigInteger.TWO, new int[]{0, 3, 0, 4, 8, 1, 7}, true), approximatePi.add(addend));
    }

    @Test
    public void testAdd3() {
        BigNumber target = BigNumber.value(3, 1415927, false);
        BigNumber addend = BigNumber.value(1, 111111, false);
        assertEquals(new BigNumber(BigInteger.valueOf(4), new int[]{2, 5, 2, 7, 0, 3, 7}, false), target.add(addend));
    }

    @Test
    public void testAdd4() {
        BigNumber target = BigNumber.value(3, 1415927, false);
        BigNumber addend = BigNumber.value(1, 111111);
        assertEquals(new BigNumber(BigInteger.TWO, new int[]{0, 3, 0, 4, 8, 1, 7}, false), target.add(addend));
    }

    @Test
    public void testAddWithDecimal2() {
        BigNumber addend = BigNumber.value(0, 240, true);
        BigNumber target = BigNumber.value(0, 20, false);
        assertEquals("0.04", target.add(addend).toString());
    }

    @Test
    public void testAddDecimal3() {
        BigNumber addend = BigNumber.value(200, 5234, false);
        BigNumber target = BigNumber.value(100, 5000, true);
        assertEquals("-100.0234", target.add(addend).toString());
    }

    @Test
    public void testAddDecimal4() {
        BigNumber addend = BigNumber.value(100, 5234, false);
        BigNumber target = BigNumber.value(235, 18924, true);
        assertEquals("134.66584", target.add(addend).toString());
    }

    @Test
    public void testAddDecimal5() {
        BigNumber addend = new BigNumber(BigInteger.ZERO, new int[]{1, 7, 6, 4}, true);
        BigNumber target = new BigNumber(BigInteger.ZERO, new int[]{0, 9}, false);
        assertEquals(new BigNumber(BigInteger.ZERO, new int[]{0, 8, 6, 4}, true), target.add(addend));
    }

    @Test
    public void testAddDecimal6() {
        BigNumber addend = BigNumber.value(200, 5234, false);
        BigNumber target = BigNumber.value(100, 5005, false);
        assertEquals("-301.0239", target.add(addend).toString());
    }

    @Test
    public void testAddDecimal7() {
        BigNumber addend = BigNumber.value(0, 5234, false);
        BigNumber target = BigNumber.value(0, 5005, true);
        assertEquals("-0.0229", target.add(addend).toString());
    }

    @Test
    public void testNegate() {
        assertEquals(new BigNumber(BigInteger.valueOf(3), new int[]{1, 4, 1, 5, 9, 2, 7}, false), BigNumber.value(3, 1415927).negate());
    }

    @Test
    public void testMultiply1() {
        assertEquals(BigNumber.value(3, 1415927, true), BigNumber.value(3, 1415927).multiply(BigNumber.one));
        assertEquals(BigNumber.value(3, 1415927, false), BigNumber.value(3, 1415927).multiply(BigNumber.one.negate()));
        assertEquals(BigNumber.value(3, 1415927, false), BigNumber.value(3, 1415927, false).multiply(BigNumber.one));
        assertEquals(BigNumber.value(3, 1415927, true), BigNumber.value(3, 1415927, false).multiply(BigNumber.one.negate()));
    }

    @Test
    public void testMultiply2() {
        assertEquals(BigNumber.value(1, 21, true), BigNumber.value(1, 1, true).multiply(BigNumber.value(1, 1, true)));
        assertEquals(BigNumber.parse("9.86960469269329"), BigNumber.value(3, 1415927, true).multiply(BigNumber.value(3, 1415927, true)));
    }

    @Test
    public void testMultiply3() {
        BigNumber piSquared = BigNumber.pi.multiply(BigNumber.pi);
        assertEquals("9.869604401089358618834490999876151135313699407240790626413349376220044822419205243001773403718552225653007487119222379933851745525268070040321103742979861060062160525374811732041221722760637532682489", piSquared.toString());
    }

    @Test
    public void testDivideLong1() {
        BigNumber target = BigNumber.value(3, 1415927, true);
        assertEquals(BigNumber.value(1L, 57079635L, true), target.divide(2));
        assertEquals(BigNumber.value(0L, 157079635L, true), target.divide(20));
    }

    @Test
    public void testDivideLong2() {
        BigNumber target = BigNumber.value(1L);
        BigNumber oneThird = target.divide(3);

        String string = oneThird.toString();
        assertEquals(1002, string.length());
        assertTrue(string.startsWith("0.3333333333333333"));
    }

    @Test
    public void testDivideBigInteger() {
        BigNumber target = BigNumber.value(3, 1415927, true);
        assertEquals(BigNumber.value(1L, 57079635L, false), target.divide(BigNumber.value(-2)));
    }

    @Test
    public void testDivideBigNumber1() {
        BigNumber target = BigNumber.value(3, 1415927, true);
        assertEquals(BigNumber.value(1L, 57079635L, true), target.divide(BigNumber.value(2)));
        assertEquals(BigNumber.value(0L, 157079635L, true), target.divide(BigNumber.value(20)));
    }

    @Test(expected = RuntimeException.class)
    public void testDivideBigNumber2() {
        BigNumber target = BigNumber.value(3, 333333, true);
        BigNumber divisor = BigNumber.value(1, 111111, true);
        assertEquals(BigNumber.value(3L), target.divide(divisor));
    }

    @Test
    public void testAsBigDecimal() {
        BigNumber target = BigNumber.value(3, 1415927, true);
        assertEquals(BigDecimal.valueOf(3.1415927), target.toBigDecimal());
        assertEquals(BigDecimal.valueOf(3.1415927), target.negate().toBigDecimal().negate());
    }

    @Test
    public void testValueOfBigDecimal() {
        BigDecimal value = BigDecimal.valueOf(3.1415927);
        BigNumber target = BigNumber.value(value);
        assertEquals(BigNumber.value(3, 1415927, true), target);
    }

    @Test
    public void testValueOfRational() {
        Try<Rational> triedValue = Rational.parse("3.1415927");
        Rational rational = triedValue.get();
        BigNumber target = BigNumber.value(rational);
        assertEquals(BigNumber.value(3, 1415927, true), target);
    }

    @Test(expected = BigNumber.BigNumberException.class)
    public void testValueOfDouble() {
        // We do not support the (double) value method because doubles are not exact.
        BigNumber.value(Math.PI);
    }

    @Test
    public void testToString() {
        assertEquals("-1", new BigNumber(-1).toString());
        assertEquals("0", new BigNumber(0).toString());
        assertEquals("3", new BigNumber(3).toString());
        assertEquals("3.1", BigNumber.value(3, 1, true).toString());
        assertEquals("3.1415927", BigNumber.value(3, 1415927, true).toString());
    }
}