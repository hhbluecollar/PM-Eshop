package edu.miu.eshop.product.entity;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@ToString

public class CartItem {

    private String productId;
    private int quantity;
    private double unitCost;
    private String vendorId;
    private String userName;

}
