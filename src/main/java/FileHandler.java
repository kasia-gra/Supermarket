import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.*;

public class FileHandler {

    String currentDir = System.getProperty("user.dir") + "/src/main/resources/";


    public List<List<String>> readPrices(String pricesList) throws FileNotFoundException {
        List<List<String>> prices = new ArrayList<List<String>>();
        try {
            String fileName = currentDir + pricesList;
            CSVReader csvReader = new CSVReader(new FileReader(fileName));
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                prices.add(Arrays.asList(values));
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (CsvValidationException csvValidationException) {
            csvValidationException.printStackTrace();
        }
        return prices;
    }


    public List<Integer> readBill(String billName) throws IOException {
        List<Integer> bill = new ArrayList<>();
        File file = new File(currentDir + billName);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = bufferedReader.readLine()) != null){
            int barcode = Integer.parseInt(line);
            bill.add(barcode);
    }
        return bill;
}

}



