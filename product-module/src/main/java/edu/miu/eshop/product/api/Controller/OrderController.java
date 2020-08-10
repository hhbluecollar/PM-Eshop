package edu.miu.eshop.product.api.Controller;

import edu.miu.eshop.product.api.client.Email;
import edu.miu.eshop.product.repository.CustomerRepository;
import edu.miu.eshop.product.entity.Order;
import edu.miu.eshop.product.entity.OrderItem;
import edu.miu.eshop.product.entity.PaymentCard;
import edu.miu.eshop.product.entity.Customer;
import edu.miu.eshop.product.entity.ShoppingCart;
import edu.miu.eshop.product.service.OrderService;
import edu.miu.eshop.product.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.email.service}")
    private String URI_EMAIL_SERVICE ;
    @Value("${path.email.sent}")
    private String  PATH_EMAIL_SEND ;

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

        // CALL PAYMENT MODULE

       //  total cost and
             //PaymentModule(order.getUserName(), id, paymentCard);

       //save purchase history

       //SEND EMAIL TO CUSTOMER
        String[] recipientsCustomer = {"springmukera@gmail.com"};
        String customerSubject = "Your Order From eshop";
        String [] attachmentsPath = {"src/main/resources/attachments/testAttachment.txt"};
        String messageCustomer = "Congratulations! Your order successfully completed /n" +
            "Order Number:" + order.getOrderNumber() + " /n" +
            "Total price: " + order.getTotalCost() + " /n" +
            "Date: " + order.getOrderDate() + " /n" ; //+
           // "Ordered by: " + order.getBuyer().getFirstName() + "  /n" +
            //"Payment Method: " + order.getMethod() + "/n" + http://localhost:8080/ecommerce/v1/

        HttpEntity<?> cusEmailEntity = prepareEmail(recipientsCustomer,customerSubject,messageCustomer,attachmentsPath);
        restTemplate.postForObject(URI_EMAIL_SERVICE + PATH_EMAIL_SEND,  cusEmailEntity, String.class);

    // SEND EMAIL TO VENDOR
        String [] recipientsVendor = {"springmukera@gmail.com"};
        String vendorSubject = "Your Order From eshop";
        String [] VendorAttachmentsPath = {"src/main/resources/attachments/testAttachment.txt"};

        String messageVendor = "New order! New order is placed /n" +
            "Order Number:" + order.getOrderNumber() + " /n" +
            "Total price: " + order.getTotalCost() + " /n" +
            "Date: " + order.getOrderDate() + " /n" ;
           // "Ordered by: " + order.getBuyer().getFirstName() + " /

        HttpEntity<?> venEmailEntity = prepareEmail(recipientsVendor,vendorSubject,messageCustomer,VendorAttachmentsPath);
        restTemplate.postForObject(URI_EMAIL_SERVICE + PATH_EMAIL_SEND,  venEmailEntity, String.class);

        //STORE ORDER DETAIL??????

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

    @GetMapping("test")
    public String test(){
        Email email = new Email();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String [] recipientsVendor = {"springmukera@gmail.com"};
        String [] ttachmentsPath = {"src/main/resources/attachments/testAttachment.txt"};
        email.setBody("testfrom order module");
        email.setSubject("TEST");
        email.setReceivers(recipientsVendor);
        email.setAttachmentsPath(ttachmentsPath);
        HttpEntity<?> entity=new HttpEntity<>(email,headers);

        restTemplate.postForObject(URI_EMAIL_SERVICE + PATH_EMAIL_SEND,  entity, String.class);
        return "test ok";
    }

    private HttpEntity prepareEmail( String [] receivers,  String subject, String body, String [] attachmentsPath){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Email email = new Email();
        email.setReceivers(receivers);
        email.setSubject(subject);
        email.setBody(body);
        email.setAttachmentsPath(attachmentsPath);
        HttpEntity<?> entity = new HttpEntity<>(email,headers);
        return  entity;
    }
}
