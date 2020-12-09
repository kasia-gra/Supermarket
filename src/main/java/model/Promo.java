package model;

import java.math.BigDecimal;

public class Promo {

    BigDecimal unitPrice;
    BigDecimal totalPrice;
    int productCode;
    int requiredQty;

    public Promo(int productCode, int requiredQty, BigDecimal unitPrice, BigDecimal totalPrice) {
        this.unitPrice = unitPrice;
        this.productCode = productCode;
        this.requiredQty =  requiredQty;
        this.totalPrice = totalPrice;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString(){
        return "promo qty: " + this.requiredQty + " unit price " + this.unitPrice + " total price " + this.totalPrice;
    }

    public int getRequiredQty() {
        return requiredQty;
    }
}


