import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import model.Product;
import model.Promo;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class FileHandler {

    private ClassLoader classLoader = getClass().getClassLoader();

    public Map<Integer, Product> readPrices(String pricesList) {
        String file = classLoader.getResource(pricesList).getFile();
        Map<Integer, Product> availableProducts = new HashMap<>();
        try {
            CSVReader csvReader = new CSVReader(new FileReader(file));
            String[] values = null;
            int lineIndex = 0;
            while ((values = csvReader.readNext()) != null) {
            if (lineIndex !=0) generatePrices(values, availableProducts);
            lineIndex ++;
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (CsvValidationException csvValidationException) {
            csvValidationException.printStackTrace();
        }
        return availableProducts;
    }


    private void generatePrices(String[] values,  Map<Integer, Product> availableProducts){
            int barcode = Integer.parseInt(values[0]);
            String productName = values[1];
            int qty = Integer.parseInt(values[2]);
            BigDecimal price = new BigDecimal(values[3]);
            BigDecimal unitPrice = price.divide(BigDecimal.valueOf(qty), 2, RoundingMode.HALF_UP);
            Product addedProduct = new Product(productName);
            if ( !availableProducts.containsKey(barcode)) {
                availableProducts.put(barcode, addedProduct);
            }
            if (qty == 1) availableProducts.get(barcode).setUnitPrice(price);
            else availableProducts.get(barcode).addPromo(new Promo(barcode, qty, unitPrice, price));
    }


    public List<Integer> readBill(String billName) throws IOException {
        List<Integer> bill = new ArrayList<>();
        String file = classLoader.getResource(billName).getFile();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = bufferedReader.readLine()) != null){
            int barcode = Integer.parseInt(line);
            bill.add(barcode);
    }
        return bill;
}

}



