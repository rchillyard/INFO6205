package edu.neu.coe.info6205;

/**
 * Class Complex.
 */
public class Complex {
    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }
    public Complex(double real) {
        this(real, 0);
    }

    public final double real;
    public final double imag;
}