import java.util.ArrayList;
import java.util.Scanner;

public class OrderedSequentialSearchST<Key extends Comparable<Key>,Value> {
    private Node first;
    private int N;

    private class Node
    {
        Key key;
        Value value;
        Node next;
        public Node(Key k, Value v, Node n)
        {
            key = k;
            value = v;
            next = n;
        }
    }
    public boolean isEmpty()
    {
        return first == null;
    }
    public int size()
    {
        return N;
    }
    public Value get(Key key)
    {
        //TODO:: Implement the get method. Returns a Value if the key exists else     returns Null.
        Node x = first;
        while (x!=null){
            if (x.key.equals(key)) return x.value;
            x = x.next;
        }
        return null;
    }

    public void set(Key key, Value value)
    {
        //TODO:: Implement the set method.
        if (first == null){
            first = new Node(key,value,null);
            return;
        }
        Node x = first;
        if (key.compareTo(x.key)<0){
            x = new Node(key,value,first);
            first = x;
            return;
        }
        if (first.key.equals(key)){
            first.value = value;
            return;
        }
        x = first;
        Node y = x.next;
        while ((y != null)&&(y.key.compareTo(key) <= 0)){
            if (y.key.equals(key)){
                y.value = value;
                return;
            }
            x = y;
            y = x.next;
        }

        x.next=new Node(key,value,y);

        N++;
    }

    public void delete(Key key)
    {
        if(contains(key)){
            //TODO:: Implement the delete method, which removes a node from the table
            if (first.key.equals(key)){
                first = first.next;
                return;
            }
            Node x = first;
            Node y = x.next;
            while (!y.key.equals(key)){
                x = y;
                y = x.next;
            }
            x.next = y.next;

            N --;
        }
    }
    public boolean contains(Key key)
    {
        return get(key) != null;
    }
    public Key min()
    {
        return first.key;
    }
    public Key max()
    {
        if(size() == 1)
            return first.key;
        Node x;
        for(x = first; x != null && x.next != null; x = x.next);

        return x.key;
    }
    public ArrayList<Key> keys()
    {
        ArrayList a = new ArrayList<>();
        for(Node x = first; x != null; x= x.next)
            a.add(x.key);
        return a;
    }

    public static void main(String[] args){
        OrderedSequentialSearchST<Integer, String> s = new OrderedSequentialSearchST<>();
        Scanner in = new Scanner(System.in);
        int lines = in.nextInt();
        for(int i=0;i<lines;i++){
            String command = in.next();
            String[] com = command.split(",");
            if(com[0].equals("0")){
                s.set(Integer.parseInt(com[1]),com[2]);
            }else if(com[0].equals("1")){
                s.delete(Integer.parseInt(com[1]));
            }
        }
        ArrayList keys = s.keys();
        for(int i=0; i<keys.size();i++){
            System.out.println(s.get((Integer) keys.get(i)));
        }
    }
}