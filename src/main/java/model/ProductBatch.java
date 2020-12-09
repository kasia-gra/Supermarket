package model;

import java.math.BigDecimal;

public class ProductBatch {

    private int batchQty;
    private BigDecimal totalPrice;

    public ProductBatch(int batchQty, BigDecimal totalPrice, int productCode){
        this.batchQty = batchQty;
        this.totalPrice = totalPrice;
    }

    public int getBatchQty() {
        return batchQty;
    }

    public BigDecimal getTotalPrice() { return totalPrice; }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        this.batchQty += 1;}

    public void addOneProduct(BigDecimal priceToBeAdded){
        totalPrice = totalPrice.add(priceToBeAdded);
        batchQty ++;
    }

    @Override
    public String toString(){
        return "PRICE: " + this.totalPrice + " QTY: " + this.batchQty;
    }

}
