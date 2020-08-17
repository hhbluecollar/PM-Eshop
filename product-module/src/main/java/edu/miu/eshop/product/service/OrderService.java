package edu.miu.eshop.product.service;

import edu.miu.eshop.product.entity.*;

import java.util.List;

public interface OrderService {
    Order createOrder(ShoppingCart cart, String userName);
    Order createGuestOrder(List<CartItem> items, String email);

    Order getOrder(String orderNumber);

    List<Order> getAllOrders(String customerId);

    void updateOrder(Order oldOrder);

    void deleteOrder(String orderNumber);

    void checkout(String cartNumber, Card paymentCard);
}
