package edu.neu.coe.info6205.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @param <T>
 * @author Harshit Raj
 */
public class FileHandlerImpl_CSV<T extends FileData> implements FileHandler<T> {
    /**
     * @param colName  Coma seprated names of columns
     * @param fileName name of file to be saved with path and extention
     * @param data     list of data where every item in list is a row
     */
    @Override
    public boolean writecsv(String colName, String fileName, List<T> data) {
        File f = new File(fileName);

        try {
            f.createNewFile();
            FileWriter fWriter = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fWriter);
            bw.write(colName);
            for (T row : data) {
                bw.write(row.toFile());
            }
            bw.close();
        } catch (IOException e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }


}
