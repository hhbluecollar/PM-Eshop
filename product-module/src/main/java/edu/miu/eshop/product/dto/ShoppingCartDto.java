package edu.miu.eshop.product.dto;

import edu.miu.eshop.product.entity.CartItem;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ShoppingCartDto {

    private String cartId;
    private String userName;
    private CartItem cartItem;
}
