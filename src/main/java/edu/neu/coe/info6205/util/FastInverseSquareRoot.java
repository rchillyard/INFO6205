package edu.neu.coe.info6205.util;

/**
 * This algorithm is explained here: https://en.wikipedia.org/wiki/Fast_inverse_square_root
 */
public class FastInverseSquareRoot {

    static float invSqrt(final float x, int optimize) {
        final int MAGIC_NUMBER = 0x5f3759df;
        final float x2 = x * 0.5f;
        int number = Float.floatToIntBits(x);
        number = MAGIC_NUMBER - (number >> 1);
        float result = Float.intBitsToFloat(number);
        while (optimize-->0) result *= (1.5f - (x2 * result * result));
        return result;
    }

    public static void main(final String[] args) {
        final float result = invSqrt(3.1415927f, 0);
        System.out.println(result);
        System.out.println(1.0f / (result * result));
    }

}