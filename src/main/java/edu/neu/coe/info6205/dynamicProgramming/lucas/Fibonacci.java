package edu.neu.coe.info6205.dynamicProgramming.lucas;

import java.util.ArrayList;

public class Fibonacci {
    public Fibonacci() {
        fib.add(0, 1);
        fib.add(1, 1);
    }

    public int get(int n) {
        if (n < 0) throw new UnsupportedOperationException("Fibonacci.get is not supported for negative n");
        if (n < fib.size()) return fib.get(n);
        return evaluate(n);
    }

    private int evaluate(int n) {
        for (int i = fib.size(); i <= n; i++) fib.add(i, fib.get(i - 2) + fib.get(i - 1));
        return fib.get(n);
    }

    final ArrayList<Integer> fib = new ArrayList<>();
}