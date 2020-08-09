package edu.miu.eshop.product.api.Controller;

import edu.miu.eshop.product.repository.CustomerRepository;
import edu.miu.eshop.product.entity.Order;
import edu.miu.eshop.product.entity.OrderItem;
import edu.miu.eshop.product.entity.PaymentCard;
import edu.miu.eshop.product.entity.Customer;
import edu.miu.eshop.product.entity.ShoppingCart;
import edu.miu.eshop.product.service.OrderService;
import edu.miu.eshop.product.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ShoppingCartService shoppingCartService;

    // ORDER CREATE
    @GetMapping("create/{userName}")
    public ResponseEntity createOrder(@PathVariable String userName){
        ShoppingCart cart = shoppingCartService.findCartForUser(userName);
        if(cart==null )  {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("The user has no order");
        }
        orderService.createOrder(cart, userName);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Order created");
    }

    //READ
    @GetMapping("/{userName}")
    public ResponseEntity getAllOrders(@PathVariable String userName){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body( orderService.getAllOrders(userName));
    }

    @GetMapping("order/{orderNumber}")
    public Order getOrder(@PathVariable("orderNumber") String orderNumber){
        return orderService.getOrder(orderNumber);
    }

    //CHECKOUT and CONFIRM PAYMENT
    @PostMapping("/checkout/{id}")
    public ResponseEntity<?> checkout(@PathVariable("id") String id, @RequestBody PaymentCard paymentCard){

        //FIND ALL PENDING ORDERS FOR THE CUSTOMER
        Order order = orderService.getOrder(id);
       // List<Order> orders = orderService.getAllOrders(customer);
        //confirm payment
        /*
        if(CardValidator.isValidCard(paymentCard.getCardNumber())==false)
            return  new ResponseEntity<>("Invalid Card Number!", HttpStatus.BAD_REQUEST);
        else {

            CALL PAYMENT MODULE
             */

      //  total cost and
             //PaymentModule(order.getUserName(), id, paymentCard);

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

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Order updated.");
    }

    //DELETE ORDER
    @DeleteMapping("delete/{orderNumber}")
    public ResponseEntity<?> deleteOrder(@PathVariable("orderNumber") String orderNumber){

        orderService.deleteOrder(orderNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Order deleted successfully.");
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
