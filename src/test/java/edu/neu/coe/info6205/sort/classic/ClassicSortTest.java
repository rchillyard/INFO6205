package edu.neu.coe.info6205.sort.classic;

import edu.neu.coe.info6205.sort.GenericHelper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class ClassicSortTest {

    class MyClass implements Classify<MyClass> {
        public MyClass(int value) {
            this.value = value;
        }

        @Override
        public int classify() {
            return value / 1000;
        }

        private final int value;

        @Override
        public String toString() {
            return "" + value;
        }
    }

    @Test
    public void mutatingSort() throws IOException {
        ClassicSort<MyClass> sorter = new ClassicSort<>();
        GenericHelper<MyClass> helper = sorter.getHelper();
        int n = 100;
        helper.init(n);
        MyClass[] xs = helper.random(MyClass.class, (random -> new MyClass(random.nextInt(100000))));
        sorter.mutatingSort(xs);
        // Check sorted
        for (int i = 1; i < n; i++) assertTrue(xs[i - 1].classify() <= xs[i].classify());
    }
}