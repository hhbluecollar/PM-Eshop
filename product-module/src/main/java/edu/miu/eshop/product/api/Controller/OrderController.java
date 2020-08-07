package edu.miu.eshop.product.api.Controller;

import edu.miu.eshop.product.repository.CustomerRepository;
import edu.miu.eshop.product.entity.Order;
import edu.miu.eshop.product.entity.OrderItem;
import edu.miu.eshop.product.entity.PaymentCard;
import edu.miu.eshop.product.entity.Customer;
import edu.miu.eshop.product.entity.ShoppingCart;
import edu.miu.eshop.product.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerRepository customerRepository;

    // ORDER CREATE
    @GetMapping("create/{userName}")
    public ResponseEntity<?> createOrder(@PathVariable String userName){
        Customer customer = customerRepository.findDistinctFirstByUserName(userName);
        ShoppingCart cart = customer.getCart();
        orderService.createOrder(cart, customer);
        return ResponseEntity.ok().body("Order created successfully.");
    }

    //READ
    @GetMapping("getAll/{userName}")
    public List<Order> getAllOrders(@PathVariable String userName){
        Customer customer = customerRepository.findDistinctFirstByUserName(userName);
        System.out.println(customer);
        return orderService.getAllOrders(customer);
    }

    @GetMapping("get/{orderNumber}")
    public Order getOrder(@PathVariable("orderNumber") String orderNumber){
        return orderService.getOrder(orderNumber);
    }

    //CHECKOUT and CONFIRM PAYMENT
    @PostMapping("/checkout/{userName}")
    public ResponseEntity<?> checkout(@PathVariable("userName") String userName, @RequestBody @Valid PaymentCard paymentCard){

        //FIND ALL PENDING ORDERS FOR THE CUSTOMER
        Customer customer = customerRepository.findDistinctFirstByUserName(userName);
        List<Order> orders = orderService.getAllOrders(customer);
        //confirm payment
        /*
        if(CardValidator.isValidCard(paymentCard.getCardNumber())==false)
            return  new ResponseEntity<>("Invalid Card Number!", HttpStatus.BAD_REQUEST);
        else {

            CALL PAYMENT MODULE
             */
            //  PaymentModule(customer, orderNumber, paymentCard);

            //save purchase history
    /*
       //SEND EMAIL
       //TO CUSTOMER
    String[] recipients = {"eshop.noreply@gmail.com"};
    String message = "Congratulations! Your order successfully completed /n" +
            "Order Number:" + order.getOrderNumber() + " /n" +
            "Total price: " + order.getTotalPrice() + " /n" +
            "Date: " + order.getOrderCompletedDate() + " /n" +
            "Ordered by: " + order.getBuyer().getFirstName() + " /n" +
            "Payment Method: " + order.getMethod() + "/n" +
            "Your order will be delivered on: " + order.getDeliveryDate() + " /n";
             SMTPMODUEL !!!!

    //TO VENDOR
    String[] recipients = {"eshop.noreply@gmail.com"};
    String message = "New order! New order is placed /n" +
            "Order Number:" + order.getOrderNumber() + " /n" +
            "Total price: " + order.getTotalPrice() + " /n" +
            "Date: " + order.getOrderCompletedDate() + " /n" +
            "Ordered by: " + order.getBuyer().getFirstName() + " /
     */
            //STORE ORDER DETAIL??????

        //}
        return ResponseEntity.ok().body("Order checkout is successfully.");
    }

    @PostMapping("guestcheckout")
    public ResponseEntity<?> guestCheckout(@RequestBody HttpSession httpSession){
        List<OrderItem> orderItems  = (List<OrderItem>)httpSession.getAttribute("orderItems");
        httpSession.getId();

        return ResponseEntity.ok().body("Order checkout is successfully.");
    }

    //UPDATE ORDER
    @PatchMapping("update/{orderNumber}")
    public ResponseEntity<?> updateOrder(@PathVariable("orderNumber") String orderNumber,@RequestBody @Valid Order newOrder){

        Order oldOrder = orderService.getOrder(orderNumber);
        //oldOrder.setOrderedBy(newOrder.getOrderedBy());
        oldOrder.setOrderDate(newOrder.getOrderDate());
        oldOrder.setTotalCost(newOrder.getTotalCost());
        oldOrder.setCartItem(newOrder.getCartItem());
        orderService.updateOrder(oldOrder);
        return ResponseEntity.ok().body("Order updated successfully.");
    }

    //DELETE ORDER
    @DeleteMapping("delete/{orderNumber}")
    public ResponseEntity<?> deleteOrder(@PathVariable("orderNumber") String orderNumber){

        orderService.deleteOrder(orderNumber);
        return ResponseEntity.ok().body("Order deleted successfully.");
    }

    //INVOICE GENERATION
    @PostMapping("/invoice/{orderNumber}")
    public String generateInvoice(@RequestBody  @Valid  Customer customer, @PathVariable("orderNumber") String  orderNumber) {

        /*
        CALL TO REPORT MODULE
         */
        // reportModule.generateInvoice(customer,orderNumber, TaxRate.TAX_RATE.getStateTax());
        return "Invoice Generated";
    }
}
