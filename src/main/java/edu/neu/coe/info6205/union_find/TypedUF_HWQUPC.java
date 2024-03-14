package edu.neu.coe.info6205.union_find;

import edu.neu.coe.info6205.SizedIterable;

import java.util.HashMap;
import java.util.Map;

public class TypedUF_HWQUPC<T> extends UF_HWQUPC implements TypedUF<T> {

    final private Map<T, Integer> map;

    public TypedUF_HWQUPC(SizedIterable<T> ts) {
        super(ts.size());
        map = new HashMap<>(ts.size());
        int count = 0;
        for (T t : ts) map.put(t, count++);
    }

    public boolean connected(T t1, T t2) throws UFException {
        return connected(lookup(t1), lookup(t2));
    }

    public void union(T t1, T t2) throws UFException {
        union(lookup(t1), lookup(t2));
    }

    private int lookup(T t) throws UFException {
        Integer x = map.get(t);
        if (x != null) return x;
        else throw new UFException("Element " + t + " does not exist");
    }
}