package edu.miu.microservice.service;

import edu.miu.microservice.entity.shoppingcart.Order;
import edu.miu.microservice.entity.shoppingcart.OrderItem;
import edu.miu.microservice.entity.shoppingcart.pattern.Customer;
import edu.miu.microservice.entity.shoppingcart.pattern.ShoppingCart;

import java.util.List;

public interface OrderService {
    void createOrder(ShoppingCart cart, Customer customer);
    void createGuestOrder(List<OrderItem> items);
    Order checkStock(Order order);

    Order getOrder(String orderNumber);

    List<Order> getAllOrders(Customer customer);

    void updateOrder(Order oldOrder);

    void deleteOrder(String orderNumber);
}
