/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harun
 */
public class ShoppingCard implements Serializable {

    private final List<ShoppingCardItem> shoppingCardItems = new ArrayList<>();

    public ShoppingCard() {
        super();
    }

    public List<ShoppingCardItem> getShoppingCardItems() {
        return shoppingCardItems;
    }

    public void addItem(Product product, int quantity) {
        for (ShoppingCardItem shoppingCardItem : shoppingCardItems) {
            if (shoppingCardItem.getProduct().getProductId() == product.getProductId()) {
                shoppingCardItem.setQuantity(shoppingCardItem.getQuantity() + quantity);
                return;
            }

        }
        ShoppingCardItem shoppingCardItem = new ShoppingCardItem(product, quantity);
        shoppingCardItems.add(shoppingCardItem);
    }
}
