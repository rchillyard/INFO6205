import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    /*
     * Complete the findResult function below.
     */

    static char count[]=new char[256];
    static int findResult(String str) {
        for(int i=0;i<str.length();i++){
            count[str.charAt(i)]++;
        }
        int index=-1;
        for(int j=0;j<str.length();j++){
            if(count[str.charAt(j)]==1){
                index=j;
                break;
            }
        }
        return index;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String str = scanner.nextLine();

        int res = findResult(str);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
