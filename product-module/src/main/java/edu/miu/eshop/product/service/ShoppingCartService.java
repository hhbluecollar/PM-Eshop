package edu.miu.eshop.product.service;




import edu.miu.eshop.product.entity.CartItem;
import edu.miu.eshop.product.entity.Customer;
import edu.miu.eshop.product.entity.Product;

import java.util.List;

public interface ShoppingCartService {

    void addCartItem(Customer cart, Product product, int quantity);

    List<CartItem> getItems(String cartId);

    void removeItem(Customer customer, String productId);

    void setProductQuantity(Customer customer, String productId, int updatedQuantity);

    void createNewCart(Customer customer);
}
