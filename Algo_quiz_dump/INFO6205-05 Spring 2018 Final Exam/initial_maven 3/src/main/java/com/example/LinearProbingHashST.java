package com.example;

public class LinearProbingHashST<Key, Value> {
    private int N; // number of key-value pairs in the table
    private int M = 16; // size of linear-probing table
    private Key[] keys; // the keys
    private Value[] vals; // the values
    public LinearProbingHashST()
    {
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    public LinearProbingHashST(int cap)
    {
        keys = (Key[]) new Object[cap];
        vals = (Value[]) new Object[cap];
        M = cap;
    }

    private int hash(Key key)
    { return (key.hashCode() & 0x7fffffff) % M; }

    public void put(Key key, Value val)
    {
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key)) { vals[i] = val; return; }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Value get(Key key)
    {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key))
                return vals[i];
        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public String getKeys() {
        String output = "";
        for (int i = 0; i < M; i++ ) {
            output = output + "(" + (keys[i] == null? "_" : keys[i]) + ")";
        }
        return output;
    }

    public void printKeys() {
        System.out.println(getKeys());
    }

    public String getVals() {
        String output = "";
        for (int i = 0; i < M; i++ ) {
            output = output + "(" + (vals[i] == null? "_" : vals[i]) + ")";
        }
        return output;
    }

    public void printVals() {
        System.out.println(getVals());
    }

    public void delete(Key key)
    {
      // To-Do:
        // boolean find=false;
        // int i;
        // for (i = hash(key); keys[i] != null; i = (i + 1) % M)
        // {
        //     if(find)
        //     {
        //         if(i==hash(keys[i]))
        //         {
        //             break;
        //         }
        //         keys[(i-1+M)%M]=keys[i];
        //         vals[(i-1+M)%M]=vals[i];
        //     }
        //     if (keys[i].equals(key))
        //     {
        //         find=true;
        //     }
        // }
        // keys[(i-1+M)%M]=null;
        // vals[(i-1+M)%M]=null;
        // N--;
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M){
            if (keys[i].equals(key)) {
                keys[i] = null;
                vals[i] = null;
                break;
            }
        }
        for(int i=0;i<M;i++)
        {
            if(keys[i]!=null)
            {
                Key tkey=keys[i];
                Value tval=vals[i];
                keys[i]=null;
                vals[i]=null;
                put(tkey,tval);
            }
        }
        N--;
    }
}