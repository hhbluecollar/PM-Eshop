package edu.miu.eshop.product.entity;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@ToString
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
