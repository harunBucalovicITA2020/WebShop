/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Harun
 */
public class ShoppingCardItem implements Serializable{
    private Product product;
    private int quantity;

    public ShoppingCardItem() {
        super();
    }

    public ShoppingCardItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public BigDecimal getTotalPrice() {
        BigDecimal total = product.getUnitPrice().multiply(BigDecimal.valueOf(quantity));
        total = total.setScale(2,RoundingMode.HALF_UP);
        return total;
    }
   
    
}
