package edu.neu.coe.info6205;

import edu.neu.coe.info6205.bqs.Queue;
import edu.neu.coe.info6205.bqs.Queue_Elements;
import edu.neu.coe.info6205.bqs.Stack;
import edu.neu.coe.info6205.bqs.Stack_LinkedList;

import java.io.InputStream;
import java.util.Scanner;
import java.util.function.Consumer;

public class RecursionAndIteration {

    static class Node {
        private final Node left;
        private final Node right;

        Node(Node left, Node right) {
            this.left = left;
            this.right = right;
        }

        void dfs(Node node, Consumer<Node> preConsumer, Consumer<Node> inConsumer, Consumer<Node> postConsumer) {
            if (node == null) return;
            preConsumer.accept(node);
            dfs(node.left, preConsumer, inConsumer, postConsumer);
            inConsumer.accept(node);
            dfs(node.right, preConsumer, inConsumer, postConsumer);
            postConsumer.accept(node);
        }
    }

    static class Element<T> {
        private final T t;
        private final Element<T> next;

        Element(T t, Element<T> next) {
            this.t = t;
            this.next = next;
        }

        void traverse(Consumer<T> preConsumer, Consumer<T> postConsumer) {
            preConsumer.accept(t);
            if (next != null) next.traverse(preConsumer, postConsumer);
            postConsumer.accept(t);
        }

        private void iterate(Consumer<T> preConsumer, Consumer<T> postConsumer) {
            Element<T> element = this;
            while (element != null) {
                preConsumer.accept(element.t);
                postConsumer.accept(element.t);
                element = element.next;
            }
        }
    }

    public static void main(String[] args) {
//				Element<Integer> list = getList123();
        doMain(getListFromInputStream(System.in));
    }

    private static Element<Integer> getListFromInputStream(InputStream inputStream) {
        Stack<Integer> inputStack = new Stack_LinkedList<>();
        Scanner scanner = new Scanner(inputStream);
        int n = scanner.nextInt();
        for (int i = n; i > 0; i--) inputStack.push(scanner.nextInt());
        Element<Integer> list = null;
        for (Integer x : inputStack) list = new Element<>(x, list);
        return list;
    }

    private static Element<Integer> getList123() {
        return new Element<>(1, new Element<>(2, new Element<>(3, null)));
    }

    static void doMain(Element<Integer> list) {
        // Recursive solution using traverse
        System.out.println("traverse:");
        Queue_Elements<Integer> preQueue = new Queue_Elements<>();
        Queue<Integer> postQueue = new Queue_Elements<>();
        list.traverse(preQueue::enqueue, postQueue::enqueue);
        System.out.println("preQueue:");
        for (Integer x : preQueue) System.out.println(x);
        System.out.println("postQueue:");
        for (Integer x : postQueue) System.out.println(x);
        preQueue.clear();

        // Iterative solution using iterate
        System.out.println("iterate:");
        java.util.Stack<Integer> postStack = new java.util.Stack<>();
        list.iterate(preQueue::enqueue, postStack::push);
        System.out.println("preQueue:");
        for (Integer x : preQueue) System.out.println(x);
        System.out.println("postStack:");
        for (Integer x : postStack) System.out.println(x);
    }
}
