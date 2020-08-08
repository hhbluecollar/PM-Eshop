package edu.miu.eshop.product.service;

import edu.miu.eshop.product.entity.Order;
import edu.miu.eshop.product.entity.OrderItem;
import edu.miu.eshop.product.entity.Customer;
import edu.miu.eshop.product.entity.ShoppingCart;

import java.util.List;

public interface OrderService {
    void createOrder(ShoppingCart cart, String userName);
    void createGuestOrder(List<OrderItem> items);

    Order getOrder(String orderNumber);

    List<Order> getAllOrders(String userName);

    void updateOrder(Order oldOrder);

    void deleteOrder(String orderNumber);
}
