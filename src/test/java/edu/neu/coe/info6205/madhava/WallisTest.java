package edu.neu.coe.info6205.madhava;

import com.phasmidsoftware.number.core.Rational;
import org.junit.Test;
import scala.math.BigInt;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class WallisTest {

    @Test
    public void testHalfPi() {
        assertEquals(Rational.apply("4/3"), Wallis.halfPi(1));
        assertEquals(Rational.apply("64/45"), Wallis.halfPi(2));
        assertFalse(Wallis.halfPi(2).isExactDouble());
    }

    @Test
    public void testPi() {
        assertEquals(2.6666666666666665, Wallis.pi(1).doubleValue(), 1E-15);
        assertFalse(Wallis.pi(1).isExact());
        assertEquals("2.66666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666", Wallis.pi(1).toString().substring(0, 100));
    }

    @Test
    public void testTerm() {
        assertEquals(Rational.apply("4/3"), Wallis.term(1));
        assertEquals(Rational.apply("16/15"), Wallis.term(2));
        assertEquals(Rational.apply("36/35"), Wallis.term(3));
    }

    @Test
    public void testConvertToBigInt() {
        BigInt target = Wallis.convertToBigInt(BigInteger.valueOf(2));
        assertEquals(2L, target.toLong());
    }

    @Test
    public void testConvertFromBigInt() {
        BigInt bigInt2 = Wallis.convertToBigInt(BigInteger.valueOf(2));
        BigInteger target = Wallis.convertFromBigInt(bigInt2);
        assertEquals(BigInteger.valueOf(2), target);
    }
}