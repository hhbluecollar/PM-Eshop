package edu.miu.eshop.product.dto;

import edu.miu.eshop.product.entity.CartItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDto {

    private String cartId;
    private String userName;
    private CartItem cartItem;
}
