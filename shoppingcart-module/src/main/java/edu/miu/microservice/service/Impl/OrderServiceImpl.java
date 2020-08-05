package edu.miu.microservice.service.Impl;

import edu.miu.microservice.constants.TaxRate;
import edu.miu.microservice.entity.search.Product;
import edu.miu.microservice.entity.shoppingcart.Order;
import edu.miu.microservice.entity.shoppingcart.OrderItem;
import edu.miu.microservice.entity.shoppingcart.pattern.CartItem;
import edu.miu.microservice.entity.shoppingcart.pattern.Customer;
import edu.miu.microservice.entity.shoppingcart.pattern.ShoppingCart;
import edu.miu.microservice.repository.CustomerRepository;
import edu.miu.microservice.repository.OrderRepository;
import edu.miu.microservice.repository.ProductRepository;
import edu.miu.microservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public void createOrder(ShoppingCart cart, Customer customer) {

        cart.getCartItems().forEach(
                cartItem -> {
                    Order order = new Order();
                    double totalCost = calculateCost(cartItem);
                    order.setTotalCost(totalCost);
                    order.setOrderDate(LocalDate.now());
                    order.setOrderedBy(customer);
                    order.setOrderNumber(generateOrderNumber());
                    orderRepository.save(order);
                }
        );
    }

    @Override
    public void createGuestOrder(List<OrderItem> orderItems) {
        orderItems.forEach(
                cartItem -> {
                    Order order = new Order();
                   // double totalCost = calculateCost(cartItem);
                    //order.setTotalCost(totalCost);
                    order.setOrderDate(LocalDate.now());
                 //   order.setOrderedBy(customer);
                    order.setOrderNumber(generateOrderNumber());
                    orderRepository.save(order);
                }
        );
    }

    @Override
    public Order getOrder(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber);
    }

    @Override
    public List<Order> getAllOrders(Customer customer) {
        return orderRepository.findAllByOrderedBy (customer);
    }


    @Override
    public void updateOrder(Order newOrder) {
        orderRepository.save(newOrder); // save is same as upsert so works as .update()
    }

    @Override
    public void deleteOrder(String orderNumber) {
        orderRepository.deleteByOrderNumber(orderNumber);
    }

    private double calculateCost(CartItem item){
        double promotion = 0;
        Product product = productRepository.findByProductId(item.getProductId());
        if(product.getPromotionPercentage()>0){
            double promotoinRate = product.getPromotionPercentage();
            promotion = item.getUnitCost()*promotoinRate;
        }
        return item.getQuantity()*(item.getUnitCost()-promotion)* TaxRate.TAX_RATE.getStateTax();
    }

    @Override
    public Order checkStock(Order order) {
        return null;
    }

    //generate order number of length 15
    private String generateOrderNumber() {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789" ;

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(15);

        for (int i = 0; i < 15; i++) {

            // generate a random number between 0 to AlphaNumericString variable length
            int index = (int)(AlphaNumericString.length() * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }
}

