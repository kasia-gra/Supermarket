import java.io.*;
import java.util.List;

public class Main {

    public static void main(String [] args) throws IOException {
        FileHandler fh = new FileHandler();
        List<Integer> bill1  = fh.readBill("bill1.txt");
        List<Integer> bill2  = fh.readBill("bill2.txt");
        Supermarket supermarket = new Supermarket(fh, "prices.csv");
        System.out.println("TOTAL PRICE FOR BILL 1 IS: " +  supermarket.scanBill(bill1));
        System.out.println("TOTAL PRICE FOR BILL 2 IS: " +  supermarket.scanBill(bill2));
    }
}

