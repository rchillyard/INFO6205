package edu.neu.coe.info6205.util;

import java.util.List;

/**
 * @param <T>
 * @author Harshit Raj
 */
public interface FileHandler<T extends FileData> {
    boolean writecsv(String colName, String fileName, List<T> data);

}
