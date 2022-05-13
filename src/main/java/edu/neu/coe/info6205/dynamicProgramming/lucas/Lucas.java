package edu.neu.coe.info6205.dynamicProgramming.lucas;

import java.util.ArrayList;

public class Lucas {
    public Lucas() {
        lucas.add(0, 2L);
        lucas.add(1, 1L);
    }

    public long get(int n) {
        if (n < 0) throw new UnsupportedOperationException("Lucas.get is not supported for negative n");
        if (n < lucas.size()) return lucas.get(n);
        return evaluate(n);
    }

    public long bad(int n) {
        if (n < 0) throw new UnsupportedOperationException("Lucas.get is not supported for negative n");
        if (n == 0) return 2L;
        if (n == 1) return 1L;
        return bad(n-2) + bad(n-1);
    }

    private long evaluate(int n) {
        for (int i = lucas.size(); i <= n; i++) lucas.add(i, lucas.get(i - 2) + lucas.get(i - 1));
        return lucas.get(n);
    }

    final ArrayList<Long> lucas = new ArrayList<>();
}
