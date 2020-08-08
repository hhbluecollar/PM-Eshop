package edu.miu.eshop.product.service.Impl;

import edu.miu.eshop.product.constants.TaxRate;
import edu.miu.eshop.product.entity.Promotion;
import edu.miu.eshop.product.repository.CustomerRepository;
import edu.miu.eshop.product.repository.OrderRepository;
import edu.miu.eshop.product.repository.ProductRepository;
import edu.miu.eshop.product.repository.PromotionRepository;
import edu.miu.eshop.product.service.OrderService;
import edu.miu.eshop.product.entity.Product;
import edu.miu.eshop.product.entity.Order;
import edu.miu.eshop.product.entity.OrderItem;
import edu.miu.eshop.product.entity.CartItem;
import edu.miu.eshop.product.entity.Customer;
import edu.miu.eshop.product.entity.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PromotionRepository promotionRepository;

    private  double promotion;

    public void createOrder(ShoppingCart cart, String userName) {
        System.out.println(cart);
        cart.getCartItems().forEach(
                cartItem -> {
                    Order order = new Order();
                    double totalCost = calculateCost(cartItem);
                    order.setTotalCost(totalCost);
                    order.setOrderDate(LocalDateTime.now());
                    order.setUserName(userName);
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
                    order.setOrderDate(LocalDateTime.now());
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
    public List<Order> getAllOrders(String userName) {
        return orderRepository.findAllByUserName (userName);
    }


    @Override
    public void updateOrder(Order newOrder) {
        orderRepository.save(newOrder); // save is same as upsert so works as .update()
    }

    @Override
    public void deleteOrder(String orderNumber) {
        orderRepository.deleteByOrderNumber(orderNumber);
    }

    private double calculateCost(CartItem item) {

        Product product = productRepository.findByProductId(item.getProductId());
        List<Promotion> promotions = promotionRepository.findByProductId(item.getProductId());
        if(promotions.size()>0){

                    promotions.stream()
                            .map(pro->pro.getPromotionPercentage())
                            .forEach(
                                    p -> {
                                        if ( p > 0) {
                                            promotion +=  item.getUnitCost() * p;
                                        }
                                    }
                            );
                }

        return item.getQuantity()*(item.getUnitCost()-promotion)* TaxRate.TAX_RATE.getStateTax();
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

