package model;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Product {

    private String productName;
    private List<Promo> promosList = new ArrayList<>();
    private BigDecimal unitPrice;


    public Product(String productName){
        this.productName = productName;
    }

    public void setUnitPrice(BigDecimal unitPrice) {this.unitPrice = unitPrice;}

    public BigDecimal getUnitPrice(){
        return this.unitPrice;
    }

    public void addPromo(Promo promo){
        promosList.add(promo);
    }

    public List<Promo> getPromosByBestPrice(){
        promosList.sort(Comparator.comparing(Promo::getUnitPrice));
        return promosList;
    }

    public Promo getBestDeal(){
        return promosList.stream().min(Comparator.comparing(Promo::getUnitPrice))
                .orElse(promosList.get(0));
    }

    public boolean hasDiscountOption() {
        return promosList.size() > 0;
    }

    @Override
    public String toString(){
        return "Name: " + this.productName + " promos: " + promosList;
    }
}
