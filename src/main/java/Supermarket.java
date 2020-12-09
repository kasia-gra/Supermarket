import model.Product;
import model.ProductBatch;
import model.Promo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Supermarket {

    Map<Integer, Product> availableProducts = new HashMap<>();
    FileHandler fileHandler;


    Supermarket(FileHandler fileHandler, String pricesList) throws FileNotFoundException {
        this.fileHandler = fileHandler;
        updateProductsList(pricesList);
    }

    public void updateProductsList(String pricesList) throws FileNotFoundException {
        List<List<String>>  records = fileHandler.readPrices(pricesList);
        for (int line = 1; line < records.size(); line ++){
            int barcode = Integer.parseInt(records.get(line).get(0));
            String productName = records.get(line).get(1);
            int qty = Integer.parseInt(records.get(line).get(2));
            BigDecimal price = new BigDecimal(records.get(line).get(3));
            BigDecimal unitPrice = price.divide(BigDecimal.valueOf(qty), 2, RoundingMode.HALF_UP);
            Product addedProduct = new Product(productName);
            if ( !availableProducts.containsKey(barcode)) {
                availableProducts.put(barcode, addedProduct);
            }
            if (qty == 1) availableProducts.get(barcode).setUnitPrice(price);
            else availableProducts.get(barcode).addPromo(new Promo(barcode, qty, unitPrice, price));

        }
    }


    public BigDecimal scanBill(List<Integer> bill) throws IOException {
        Map<Integer, ProductBatch> scannedProducts = new HashMap<>();
        BigDecimal totalPrice = new BigDecimal(0);
        for (int productCode : bill) {
            Product currentProduct = availableProducts.get(productCode);
            if (!scannedProducts.containsKey(productCode)) if (!currentProduct.hasDiscountOption()) {
                totalPrice = totalPrice.add(currentProduct.getUnitPrice());
            } else {
                scannedProducts.put(productCode, new ProductBatch(1, currentProduct.getUnitPrice(), productCode));
            }
            else {
                ProductBatch currentBatch = scannedProducts.get(productCode);
                Promo bestDeal = currentProduct.getBestDeal();
                if (currentBatch.getBatchQty() + 1 == bestDeal.getRequiredQty()){
                    totalPrice = totalPrice.add(bestDeal.getTotalPrice());
                    scannedProducts.remove(productCode);
                }
                else {
                    ProductBatch updatedProductBatch = findPromoForCurrentQty(currentProduct, currentBatch);
                    scannedProducts.put(productCode, updatedProductBatch);
                }
            }
        }
        totalPrice = totalPrice.add(sumOfRemainingProducts(scannedProducts));
        return totalPrice;
    }


    private BigDecimal sumOfRemainingProducts(Map<Integer, ProductBatch> scannedProducts){
        BigDecimal totalPriceRemainingProducts = scannedProducts.values()
                .stream()
                .map(productBatch -> productBatch.getTotalPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalPriceRemainingProducts;
    }


    private ProductBatch findPromoForCurrentQty(Product product, ProductBatch productBatch){
        Optional<Promo> foundPromo = product.getPromosByBestPrice().stream().filter(promo -> promo.getRequiredQty() == productBatch.getBatchQty() + 1).findFirst();
        if (foundPromo.isEmpty()) {
            productBatch.addOneProduct(product.getUnitPrice());
        }
        else {
            productBatch.setTotalPrice(foundPromo.get().getTotalPrice());
        }
        return productBatch;
    }


}











