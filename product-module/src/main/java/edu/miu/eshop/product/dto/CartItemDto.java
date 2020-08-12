package edu.miu.eshop.product.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class CartItemDto {
    private String productId;
    private int quantity;
    private String userName;
    private double unitCost;
    private String vendorId;

}
