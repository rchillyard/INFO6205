package edu.neu.coe.info6205.dynamicProgramming.coins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class CoinChange {

    public static void main(String[] args) {
        int amount = Stream.of(args).map(Integer::valueOf).limit(1).mapToInt(x1 -> x1).toArray()[0];
        CoinChange coinChange = new CoinChange(Stream.of(args).map(Integer::valueOf).skip(1).mapToInt(x -> x).toArray());
        coinChange.solveForAmount(amount);
    }

    public CoinChange(int[] coins) {
        this.coins = coins;
        Arrays.sort(coins);
    }

    /**
     * Method to return the smallest number of coins which can make up the amount.
     *
     * @param amount the amount for which we need change.
     * @return the number of coins required.
     */
    public int solveForAmount(int amount) {
        Node root = new Node(amount);
        boolean satisfied = root.expand();
        if (satisfied)
            root.traverse(System.out::println);
        return 0;
    }

    class Node {

        public int getRequired() {
            return required;
        }

        public boolean expand() {
//            System.out.println("Expand: " + this);
            if (required > 0) {
                for (int i = coins.length; i > 0; i--) {
                    if (coins[i - 1] <= required) {
                        Node node = new Node(coins[i - 1], this);
                        if (node.expand()) {
                            add(node);
                            break;
                        }
                    }
                }
                return children.size() > 0;
            } else
                return true;
        }

        public Node(int coin, int required, Node parent) {
            this.coin = coin;
            this.required = required;
            this.parent = parent;
            children = new ArrayList<>();
            if (coin > 0 && parent != null) nCoins = parent.nCoins + 1;
        }

        public Node(int coin, Node parent) {
            this(coin, parent.getRequired() - coin, parent);
        }

        public Node(int amount) {
            this(0, amount, null);
        }

        public void traverse(Consumer<String> consumer) {
            consumer.accept(toString());
            children.forEach(n -> n.traverse(consumer));
        }

        @Override
        public String toString() {
            return "Node: " + "nCoins: " + nCoins + ", " + "coin: " + coin + ", " + "required: " + required;
        }

        private final int required;
        private final Node parent;
        private final int coin;
        private int nCoins = 0;

        public boolean add(Node node) {
            return children.add(node);
        }

        private List<Node> children;
    }

    private final int[] coins;
}