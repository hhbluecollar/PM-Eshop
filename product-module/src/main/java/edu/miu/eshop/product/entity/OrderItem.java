package edu.miu.eshop.product.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@ToString
@Setter
@Getter
@AllArgsConstructor
public class OrderItem {

    private String productId;
    private double price;
    private int quantity;
    private String vendorId;
}
