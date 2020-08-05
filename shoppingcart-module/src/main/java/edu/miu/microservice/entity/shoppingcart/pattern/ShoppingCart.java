package edu.miu.microservice.entity.shoppingcart.pattern;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@ToString
@Setter
@Getter
public class ShoppingCart {

    //private String id;
    // OneToMany --- the one side owns the relationship
    private List<CartItem> cartItems = new ArrayList<>();

   // private Customer owner;

    public void addItem( String productId, String name, double unitCost, int quantity) {

        CartItem item = new CartItem( productId,  name, unitCost, quantity);
        cartItems.add(item);
    }

//    ShoppingCart(Customer cus) {
//        this.owner = cus;
//    }
}
