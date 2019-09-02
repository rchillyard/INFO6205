package edu.neu.coe.info6205.coupling;

public class CouplingNone {

    class A {
        public A(int a) {
            this.a = a;
        }

        public int getA() {
            return a;
        }

        private final int a;
    }

    class B {
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
        A a = c.new A(0);
        int x = a.getA();
        B b = c.new B(1);
        int y = b.getB();
    }
}
