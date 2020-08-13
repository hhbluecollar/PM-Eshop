package edu.miu.eshop.product.service;




import edu.miu.eshop.product.dto.ProductDto;
import edu.miu.eshop.product.entity.CartItem;
import edu.miu.eshop.product.entity.Customer;
import edu.miu.eshop.product.entity.Product;
import edu.miu.eshop.product.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    void addCartItem(ProductDto product,  ShoppingCart cart, int quantity);

    List<CartItem> getItems(String cartId);

    void removeItem(ShoppingCart cart, String productId);

    void setProductQuantity(ShoppingCart cart, String productId, int updatedQuantity);

    ShoppingCart createNewCart(String userName );

    ShoppingCart findCartForUser(String userName);

    ShoppingCart findCartItem(String userName);
}
