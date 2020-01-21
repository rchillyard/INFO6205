package edu.neu.coe.info6205.util;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class SourceTest {

    @Test(expected = java.io.IOException.class)
    public void constructSource1() throws IOException {
        new Source((InputStream) null);
    }
    @Test(expected = java.lang.NullPointerException.class)
    public void constructSource2() throws IOException {
        new Source((File) null);
    }
    @Test(expected = java.lang.NullPointerException.class)
    public void constructSource3() throws IOException {
        new Source((String) null);
    }
    @Test(expected = java.io.FileNotFoundException.class)
    public void constructSource4() throws IOException {
        new Source("junk");
    }
    @Test(expected = java.io.IOException.class)
    public void constructSource5() throws IOException {
        new Source(SourceTest.class, "junk");
    }
    @Test
    public void getLines() throws IOException {
        Source source = new Source(SourceTest.class, "file1.txt");
        Stream<String> lines = source.getLines();
//        lines.forEach(System.out::println);
        assertEquals(2L, lines.count());
        lines.close();
    }
}