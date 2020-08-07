package edu.miu.eshop.product.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class CartItem {

    private String productId;
    private String productName ;
    private int quantity;
    private double unitCost;

    public CartItem(String productId, String name, double unitCost, int quantity ) {

        this.productId = productId;
        this.productName = name;
        this.quantity = quantity;
        this.unitCost = unitCost;
    }
}
