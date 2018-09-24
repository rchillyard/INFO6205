package edu.neu.coe.info6205.util;

import java.util.List;
/**
 * 
 * @author Harshit Raj
 *
 * @param <T>
 */
public interface FileHandler<T extends FileData> {
	boolean writecsv(String colName, String fileName, List<T> data);

}
