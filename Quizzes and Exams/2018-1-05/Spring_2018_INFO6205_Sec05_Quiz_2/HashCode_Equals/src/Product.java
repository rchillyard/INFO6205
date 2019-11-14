import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.time.LocalDateTime;

public class Product {
    private String productName;
    private int size;
    private LocalDateTime timestamp;

    public Product(String productName, int size) {
        this.productName = productName;
        this.size = size;
        this.timestamp = LocalDateTime.now();
    }

    public LocalDateTime updateTimeStamp() {
        this.timestamp = LocalDateTime.now();
        return this.timestamp;
    }
    @Override
    public boolean equals(Object o) {
        // ******** change the underline part ********
        if (this == o) {
            return true;
        }

        // ******** change the underline part ********
        if (o == null) {
            return false;
        }

        // ******** change the underline part ********
        if (getClass() != o.getClass()) {
            return false;
        }

        // ******** change the underline part ********
        Product p = (Product)o;
        if(!p.productName.equals(this.productName)||p.size!=this.size)
            return false;
        else
            return true;
    }

    @Override
    public int hashCode() {
        int result = 7;

        // ******** Please ONLY uncomment the correct lines ********
        result = result * 31 + size;
        result = result * 31 + ((productName == null) ? 0 : productName.hashCode());
        // result = result * 31 + (timestamp.hashCode());
        return result;
    }
    public static void main(String args[] ) throws Exception {
        Scanner scan = new Scanner(System.in);

        String productName = scan.next();
        int size = scan.nextInt();
        Product p1 = new Product(productName, size);

        productName = scan.next();
        size = scan.nextInt();
        Product p2 = new Product(productName, size);

        productName = scan.next();
        size = scan.nextInt();
        Product p3 = new Product(productName, size);

        productName = scan.next();
        size = scan.nextInt();
        Product p4 = new Product(productName, size);

        System.out.println(p1.equals(p2));
        System.out.println(p1.hashCode() == p2.hashCode());
        System.out.println(p1.equals(p3));
        System.out.println(p1.hashCode() == p3.hashCode());
        System.out.println(p1.equals(p4));
        System.out.println(p1.hashCode() == p4.hashCode());
    }
}
