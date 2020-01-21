package edu.neu.coe.info6205.util;

import java.io.*;
import java.util.stream.Stream;

public class Source {
    private final InputStream inputStream;

    public Source(InputStream inputStream) throws IOException {
        if (inputStream==null) throw new IOException("inputStream is null");
        this.inputStream = inputStream;
    }

    public Source(File file) throws IOException {
        this(new FileInputStream(file));
    }

    public Source(String pathname) throws IOException {
        this(new File(pathname));
    }

    public Source(Class<?> clazz, String resourceName) throws IOException {
        this(getResourceAsStream(clazz, resourceName));
    }

    private static InputStream getResourceAsStream(Class<?> clazz, String resourceName) throws IOException {
        InputStream stream = clazz.getResourceAsStream(resourceName);
        if (stream==null) throw new IOException(clazz+" cannot find resource "+resourceName);
        return stream;
    }

    /**
     * Method to get this Source as a Stream of String representing lines.
     *
     * NOTE: the caller is expected to close the resulting Stream.
     *
     * @return a Stream of Strings.
     * @throws IOException if something goes wrong.
     */
    public Stream<String> getLines() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        Stream<String> result = bufferedReader.lines();
//        bufferedReader.close();
        return result;
    }
}
