import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class LinearProbingHashST<Key, Value> {

    private int n;           // number of key-value pairs in the symbol table
    private int m;           // size of linear probing table
    private Key[] keys;      // the keys
    private Value[] vals;    // the values
    private int k;

    public LinearProbingHashST(int capacity,int kFactor) {
        m = capacity;
        n = 0;
        k = kFactor;
        keys = (Key[])   new Object[m];
        vals = (Value[]) new Object[m];
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    // hash function for keys - returns value between 0 and M-1
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    // resizes the hash table to the given capacity by re-hashing all of the keys
    private void resize(int capacity) {
        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<Key, Value>(capacity,k);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], vals[i]);
            }
        }
        keys = temp.keys;
        vals = temp.vals;
        m    = temp.m;
    }
    public void put(Key key, Value val) {
        if (key == null) return;

        // double table size if 50% full
        if (n >= m/2) resize(2*m);

        //Implement put. Linearly probe accoring to the "k Factor
        int i;
        for (i = hash(key); keys[i] != null; i = (i + k) % m) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        //Implement get
        for (int i = hash(key); keys[i] != null; i = (i + k) % m)
            if (keys[i].equals(key))
                return vals[i];
        return null;
    }
    public Iterable<Key> keys() {
        Queue<Key> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++)
            if (keys[i] != null) queue.add(keys[i]);
        return queue;
    }

    private boolean check() {

        // check that hash table is at most 50% full
        if (m < 2*n) {
            System.err.println("Hash table size m = " + m + "; array size n = " + n);
            return false;
        }

        // check that each key in table can be found by get()
        for (int i = 0; i < m; i++) {
            if (keys[i] == null) continue;
            else if (get(keys[i]) != vals[i]) {
                System.err.println("get[" + keys[i] + "] = " + get(keys[i]) + "; vals[i] = " + vals[i]);
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int kFactor = in.nextInt();
        int lines = in.nextInt();
        LinearProbingHashST<Integer, Integer> st = new LinearProbingHashST<Integer, Integer>(10,kFactor);
        for(int i=0;i<lines;i++){
            String command = in.next();
            String[] com = command.split(",");
            st.put(Integer.parseInt(com[0]),Integer.parseInt(com[1]));
        }
        for (int k=0;k<st.m;k++)
            System.out.print(st.get(k)+" ");
    }
}