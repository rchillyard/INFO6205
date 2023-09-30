package edu.neu.coe.info6205.coupling;

public class CouplingNone {

    static class A {
        public A(int a) {
            this.a = a;
        }

        public int getA() {
            return a;
        }

        private final int a;
    }

    static class B {
        public B(int b) {
            this.b = b;
        }

        public int getB() {
            return b;
        }

        private final int b;
    }

    public static void main(String[] args) {
        CouplingNone c = new CouplingNone();
        A a = new A(0);
        int x = a.getA();
        B b = new B(1);
        int y = b.getB();
    }
}