package edu.neu.coe.info6205.dynamicProgramming.lucas;

import java.util.ArrayList;

public class Pell {
    public Pell() {
        pell.add(0, 0L);
        pell.add(1, 1L);
    }

    public long get(int n) {
        if (n < 0) throw new UnsupportedOperationException("Pell.get is not supported for negative n");
        if (n < pell.size()) return pell.get(n);
        return evaluate(n);
    }

    private long evaluate(int n) {
        for (int i = pell.size(); i <= n; i++) pell.add(i, pell.get(i - 2) + 2 * pell.get(i - 1));
        return pell.get(n);
    }

    final ArrayList<Long> pell = new ArrayList<>();
}