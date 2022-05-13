package edu.neu.coe.info6205;

import java.io.PrintWriter;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.IntFunction;
import java.util.stream.Stream;

public class HuffmanCoding {

    public HuffmanCoding(PriorityQueue<Node> priorityQueue) {
        this.priorityQueue = priorityQueue;
    }

    public HuffmanCoding() {
        this(new PriorityQueue<>());
    }

    public Node generateCode() {
        while (priorityQueue.size() > 1) {
            Node node0 = priorityQueue.poll();
            Node node1 = priorityQueue.poll();
            assert node1 != null;
            add(new Node(node0.frequency + node1.frequency, node0.symbol + "-" + node1.symbol, node0, node1));
        }
        return priorityQueue.poll();
    }

    public void add(Node node) {
        priorityQueue.add(node);
    }

    private final PriorityQueue<Node> priorityQueue;

    static class Value {
        public Value(long x, int available) {
            this.x = x;
            this.available = available;
        }

        public Value(long x) {
            this(x, 64);
        }

        public Code encode(Code code) {
            Code result = null;
            long val = code.value;
            int len = code.length;
            if (available < len) {
                int shiftVal = 64 - len + available;
                result = new Code(val << shiftVal >> shiftVal, len - available);
                val = val >> (len - available);
                len = available;
            }
            encode(val, len);
            return result;
        }

        private void encode(long value, int length) {
            x = x << length | value;
            available -= length;
        }

        public void close() {
            encode(0L, available);
        }

        long x;
        int available;
    }

    static class Code {
        public Code(long value, int length) {
            this.value = value;
            this.length = length;
        }

        public Code add(int x) {
            // FIXME by replacing the following code
             return null;
            // END 
        }

        @Override
        public String toString() {
            String prefix = "00000000000000000000000000000";
            String s = prefix + Long.toBinaryString(value);
            return s.substring(s.length() - length);
        }

        final long value;
        final int length;
    }

    public static class HuffmanEncoder {
        public HuffmanEncoder(Node node) {
            encoder = getEncoder(node);
        }

        public Long[] encode(String[] symbols) {
            List<Value> values = new ArrayList<>();
            Value current = new Value(0L);
            for (String symbol : symbols) {
                Code code = get(symbol);
                if (code == null)
                    throw new RuntimeException("unknown symbol: " + symbol);
                Code result = current.encode(code);
                if (result != null) {
                    values.add(current);
                    current = new Value(0L);
                    current.encode(code);
                }
            }
            current.close();
            values.add(current);
            Value[] xs = values.toArray(new Value[0]);
            Stream<Long> longStream = Stream.of(xs).map(v -> v.x);
            IntFunction<Long[]> intFunction = value -> new Long[xs.length];
            return longStream.toArray(intFunction);
        }

        public Code get(String key) {
            char c = 0xFE0F;
            Code code = encoder.get(key);
            if (code == null) code = encoder.get(key + c);
            return code;
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            for (String key : encoder.keySet()) result.append(key).append("=").append(encoder.get(key)).append("\n");
            return result.toString();
        }

        private Map<String, Code> getEncoder(Node node) {
            Map<String, Code> result = new HashMap<>();
            // FIXME
            // END 
            return result;
        }

        private final Map<String, Code> encoder;
    }

    public static class HuffmanDecoder {
        public HuffmanDecoder(Node node) {
            this.node = node;
        }

        public String decode(long[] array) {
            StringBuilder stringBuilder = new StringBuilder();
            Node state = node;
            for (long x : array) {
                state = decode(stringBuilder, state, x);
                if (state == null) break;
            }
            return stringBuilder.toString();
        }

        private Node decode(StringBuilder stringBuilder, Node state, long x) {
            // FIXME
            // END 
            return state;
        }

        private final Node node;
    }

    static class Node implements Comparable<Node> {
        @Override
        public int compareTo(Node o) {
            return Integer.compare(frequency, o.frequency);
        }

        public Node(int frequency,
                    String symbol,
                    Node zero,
                    Node one) {
            this.frequency = frequency;
            this.symbol = symbol;
            this.zero = zero;
            this.one = one;
        }

        public Node(String symbol, int frequency) {
            this(frequency, symbol, null, null);
        }

        public boolean isLeaf() {
            return one == null & zero == null;
        }

        public void dfs(BiFunction<Object, Integer, Object> depthFunction, BiConsumer<Node, Object> consumer, Object depthIndicator, Integer branch) {
            // FIXME
            // END 
        }


        private final int frequency;
        private final String symbol;
        private final Node zero;
        private final Node one;
    }

    public static void main(String[] args) {
        HuffmanCoding huffmanCoding = createHuffmanCoding();
        Node tree = huffmanCoding.generateCode();
        HuffmanEncoder huffmanEncoder = new HuffmanEncoder(tree);
        showEncoder(huffmanEncoder);
        HuffmanDecoder huffmanDecoder = new HuffmanDecoder(tree);
        showTree(tree);
    }

    static String[] parseLin(String w) {
//        char c = 0xFE0F;
//        StringBuffer z = new StringBuffer();
//        z.append(c);
//        StringBuffer y = new StringBuffer();
//        String[] splits = w.toUpperCase().replace(z,y).replaceAll("S", "♠").replaceAll("H", "♥️").replaceAll("D", "♦").replaceAll("C", "♣").split("\\|");
        String[] splits = w.toUpperCase().split("\\|");
//        System.out.println(Arrays.toString(splits));
        String deal = splits[5].substring(2);
        List<String> result = new ArrayList<>(stringToStrings(deal));
        for (int i = 13; i < splits.length; i += 2)
            result.addAll(stringToStrings(splits[i]));
        result.add(null);
        return result.toArray(new String[0]);
    }

    private static List<String> stringToStrings(String w) {
        List<String> result = new ArrayList<>();
        char[] chars = w.toCharArray();
        for (char x : chars) if (x!=' ' && x!=',') result.add(x+"");
        return result;
    }

    static HuffmanCoding createHuffmanCoding() {
        HuffmanCoding huffmanCoding = new HuffmanCoding();
        huffmanCoding.add(new Node("W", 28));
        huffmanCoding.add(new Node("N", 28));
        huffmanCoding.add(new Node("E", 28));
        huffmanCoding.add(new Node("Z", 28)); // currently, we don't actually use S
        huffmanCoding.add(new Node("P", 20));
        huffmanCoding.add(new Node("1", 12));
        huffmanCoding.add(new Node("2", 14));
        huffmanCoding.add(new Node("3", 12));
        huffmanCoding.add(new Node("S️", 12)); // NOTE: this is now the same as South
        huffmanCoding.add(new Node("H️", 12));
        huffmanCoding.add(new Node("D️", 12));
        huffmanCoding.add(new Node("C️", 12));
//        huffmanCoding.add(new Node(12, "♠️"));
//        huffmanCoding.add(new Node(12, "♥️"));
//        huffmanCoding.add(new Node(12, "♦️"));
//        huffmanCoding.add(new Node(12, "♣️"));
        huffmanCoding.add(new Node("4", 11));
        huffmanCoding.add(new Node("5", 10));
        huffmanCoding.add(new Node("6", 9));
        huffmanCoding.add(new Node("7", 8));
        huffmanCoding.add(new Node("8", 8));
        huffmanCoding.add(new Node("9", 8));
        huffmanCoding.add(new Node("T", 8));
        huffmanCoding.add(new Node("A", 8));
        huffmanCoding.add(new Node("K", 8));
        huffmanCoding.add(new Node("Q", 8));
        huffmanCoding.add(new Node("J", 8));
        huffmanCoding.add(new Node("NT", 3));
        huffmanCoding.add(new Node("X", 2));
        huffmanCoding.add(new Node(null, 1));
        huffmanCoding.add(new Node("XX", 1));
        return huffmanCoding;
    }

    public static void showTree(Node tree) {
        PrintWriter pw = new PrintWriter(System.out);
        showTree(tree, pw);
        pw.flush();
        pw.close();
    }

    public static void showTree(Node tree, PrintWriter pw) {
        tree.dfs((o, b) -> o + "  " + (b != null ? b : ""),
                (node, o) -> pw.println(o + ": " + node.symbol + " (" + node.frequency + ") "),
                "", null);
    }

    public static void showEncoder(final HuffmanEncoder encoder) {
        System.out.println(encoder.toString());
    }

}
