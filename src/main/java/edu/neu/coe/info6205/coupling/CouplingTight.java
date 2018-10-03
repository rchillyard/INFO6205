package edu.neu.coe.info6205.coupling;

public class CouplingTight {

    class A {
        public A(int a) {
            CouplingTight.this.a = a;
        }

        public int getA() {
            return a;
        }
    }

    class B {
        public B(int b) {
            CouplingTight.this.b = b;
        }

        public int getB() {
            return b;
        }
    }

    private int a;
    private int b;

    public static void main(String[] args) {
        CouplingTight c = new CouplingTight();
        A a = c.new A(0);
        int x = a.getA();
        B b = c.new B(1);
        int y = b.getB();
    }
}
