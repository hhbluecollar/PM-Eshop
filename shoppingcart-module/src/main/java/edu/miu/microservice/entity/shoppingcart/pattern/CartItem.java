package edu.miu.microservice.entity.shoppingcart.pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
