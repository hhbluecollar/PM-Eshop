package edu.miu.microservice.service;


import edu.miu.microservice.entity.search.Product;
import edu.miu.microservice.entity.shoppingcart.OrderItem;
import edu.miu.microservice.entity.shoppingcart.pattern.CartItem;
import edu.miu.microservice.entity.shoppingcart.pattern.Customer;
import edu.miu.microservice.entity.shoppingcart.pattern.ShoppingCart;

import java.util.List;
import java.util.Set;

public interface ShoppingCartService {

    void addCartItem(Customer cart, Product product, int quantity);

    List<CartItem> getItems(String cartId);

    void removeItem(Customer customer, String productId);

    void setProductQuantity(Customer customer, String productId, int updatedQuantity);

    void createNewCart(Customer customer);
}
