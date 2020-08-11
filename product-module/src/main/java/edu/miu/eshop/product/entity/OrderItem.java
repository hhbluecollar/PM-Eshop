package edu.miu.eshop.product.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@ToString
@Setter
@Getter
@Document
@AllArgsConstructor
public class OrderItem {

    @Id
    public String id;
    private String productId;
    private double price;
    private int quantity;

    public OrderItem(double price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }
}
