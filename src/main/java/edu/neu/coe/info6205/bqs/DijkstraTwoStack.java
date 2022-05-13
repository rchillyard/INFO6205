package edu.neu.coe.info6205.bqs;

import java.util.StringTokenizer;

public class DijkstraTwoStack {

    private final StringTokenizer tokenizer;
    private int parentheses = 0;
    private final Stack<String> opStack = new Stack_LinkedList<>();
    private final Stack<Number> valStack = new Stack_LinkedList<>();

    public DijkstraTwoStack(String infix) {
        tokenizer = new StringTokenizer(infix);
    }

    public Number evaluate() throws BQSException {
        while (tokenizer.hasMoreTokens())
            processToken(tokenizer.nextToken());
        if (parentheses != 0)
            throw new BQSException("there are " + parentheses + " superfluous parentheses (net)");
        while (!opStack.isEmpty())
            operate();
        Number result = valStack.pop();
        if (!valStack.isEmpty())
            throw new BQSException("there are superfluous values");
        return result;
    }

    private void processToken(String s) throws BQSException {
        if (s.equals("("))
            parentheses++;
        else if (s.equals(")")) {
            parentheses--;
            operate();
        } else if ("+-*/^%".contains(s))
            opStack.push(s);
        else {
            Number n = Integer.parseInt(s);
            valStack.push(n);
        }
    }

    private void operate() throws BQSException {
        Integer y = (Integer) valStack.pop();
        Integer x = (Integer) valStack.pop();
        switch (opStack.pop()) {
            case "+":
                valStack.push(x + y);
                break;
            case "-":
                valStack.push(x - y);
                break;
            case "*":
                valStack.push(x * y);
                break;
            case "/":
                valStack.push(x / y);
                break;
            default:
                throw new BQSException("operator not recognized: " + opStack.pop());
        }
    }

    public static void main(String[] args) {
        try {
            DijkstraTwoStack twoStack = new DijkstraTwoStack("2 * ( 4 - 3 )");
            System.out.println(twoStack.evaluate());
        } catch (BQSException e) {
            e.printStackTrace();
        }
    }
}
