package edu.miu.eshop.product.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@ToString
@Setter
@Getter
@Document
public class ShoppingCart {

    @Id
    private String id;
    // OneToMany --- the one side owns the relationship
    private List<CartItem> cartItems ;
    @Indexed(unique = true)
    private String userName;

    public ShoppingCart(String userName){
        this.userName = userName;
        cartItems = new ArrayList<>();
    }
    public void addItem( String productId, String name, double unitCost, int quantity) {
        CartItem item = new CartItem( productId,  name, unitCost, quantity);
        cartItems.add(item);
    }
}
