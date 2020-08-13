package edu.miu.eshop.product.service.Impl;

import edu.miu.eshop.product.constants.TaxRate;
import edu.miu.eshop.product.dto.*;
import edu.miu.eshop.product.entity.*;
import edu.miu.eshop.product.repository.OrderRepository;
import edu.miu.eshop.product.repository.ProductRepository;
import edu.miu.eshop.product.repository.PromotionRepository;
import edu.miu.eshop.product.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static edu.miu.eshop.product.constants.TaxRate.TAX_RATE;

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

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.email.service}")
    private String URI_EMAIL_SERVICE ;
    @Value("${path.email.sent}")
    private String  PATH_EMAIL_SEND ;

    @Value("${url.user.service}")
    private String URI_USER_SERVICE ;
    @Value("${path.user.id}")
    private String  PATH_USER_GET;

    @Value("${url.payment.service}")
    private String URI_PAYMENT_SERVICE;
    @Value("${url.payment.pay}")
    private String PATH_PAYMENT_PAY;

    @Value("${url.vendor.service}")
    private String URI_VENDOR_SERVICE;
    @Value("${url.vendor.pay}")
    private String PATH_VENDOR_EMAIL;

    @Value("${admin.token}")
    private String  token ;


    public void createOrder(ShoppingCart cart, String userName) {
        Order order = new Order();
        double totalCost = calculateCost(cart);
        order.setTotalCost(totalCost);
        order.setOrderDate(LocalDateTime.now());
        order.setUserName(userName);
        order.setOrderNumber(generateOrderNumber());

        cart.getCartItems().forEach(
                cartItem -> {
                    order.addOrderItem(cartItem);
                }
        );
        orderRepository.save(order);

    }

    @Override
    public void createGuestOrder(List<CartItem> orderItems, String email) {
        ShoppingCart guestCart = new ShoppingCart();
        guestCart.setCartItems(orderItems);
        Order order = new Order();
        double totalCost = calculateCost(guestCart);
        order.setTotalCost(totalCost);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderNumber(generateOrderNumber());
        order.setUserName(email);

        orderItems.forEach(
                cartItem -> {    order.addOrderItem(cartItem);
                }
        );
        orderRepository.save(order);

    }

    @Override
    public Order getOrder(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber);
    }

    @Override
    public List<Order> getAllOrders(String customerId) {
        return orderRepository.findAllByCustomerId( customerId);
    }


    @Override
    public void updateOrder(Order newOrder) {
        orderRepository.save(newOrder); // save is same as upsert so works as .update()
    }

    @Override
    public void deleteOrder(String orderNumber) {
        orderRepository.deleteByOrderNumber(orderNumber);
    }

    @Override
    public void checkout(String orderNumber, Card paymentCard) {
        //FIND ALL PENDING ORDERS FOR THE CUSTOMER
        Order order = getOrder(orderNumber);
        String customerId = order.getCustomerId();
        // CALL PAYMENT MODULE
        RestTemplate rPay = new RestTemplate();
        HttpHeaders headersPay = new HttpHeaders();
        headersPay.setContentType(MediaType.APPLICATION_JSON);
        TransactionDto transactional = new TransactionDto(paymentCard, order.getTotalCost());
        String urlPay =  URI_PAYMENT_SERVICE +  PATH_PAYMENT_PAY;
        HttpEntity paymentEntity = new HttpEntity(transactional, headersPay);
        BooleanDto paymentOk =   rPay.exchange(urlPay,
                HttpMethod.POST,
                paymentEntity,
                BooleanDto.class).getBody();

        // CONNECT TO CUSTOMER MODULE
        RestTemplate rCus = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> customerEntity = new HttpEntity<>(headers);
        String urlCustomer =  URI_USER_SERVICE +  PATH_USER_GET;
        CustomerDto customerDto =   rCus.exchange(urlCustomer + "/" +customerId,
                HttpMethod.GET,
                customerEntity,
                CustomerDto.class).getBody();

        // SEND EMAIL TO VENDOR
        sendEmailToCustomer(customerDto, order);

        //RETRIEVE EACH VENDOR AND  SEND EMAIL
        RestTemplate rVen = new RestTemplate();
        HttpHeaders headersVendor = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> vendorEntity = new HttpEntity<>(headersVendor);

        order.getOrderItems().stream().forEach( orderItem -> {
            String urlVendor =  URI_VENDOR_SERVICE +  PATH_VENDOR_EMAIL;

            EmailDto  emailDto =   rVen.exchange(urlVendor + "/" + orderItem.getVendorId(),
            HttpMethod.GET,
                    vendorEntity,
                    EmailDto.class).getBody();
            sendEmailToVendor(emailDto.getEmail(), order);
        }
        );

        //STORE ORDER DETAIL
    }

    private double calculateCost(ShoppingCart cart) {
        int totalCost = 0;
        for (CartItem item : cart.getCartItems()){
            Product product = productRepository.findByProductId(item.getProductId());
        List<Promotion> promotions = promotionRepository.findByProductId(item.getProductId());
        if (promotions.size() > 0) {

            promotions.stream()
                    .map(pro -> pro.getPromotionPercentage())
                    .forEach(
                            p -> {
                                if (p > 0) {
                                    promotion += item.getUnitCost() * p;
                                }
                            }
                    );
        }
            totalCost+= item.getQuantity()*(item.getUnitCost()-promotion);

        }
        return  totalCost + totalCost*TAX_RATE.getStateTax();
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

    private void sendEmailToCustomer(CustomerDto customerDto, Order order){
        String[] recipientsCustomer = {customerDto.getUsername()};
        String customerSubject = "Your Order From eshop";
        String [] attachmentsPath = {"src/main/resources/attachments/testAttachment.txt"};
        String messageCustomer = "Congratulations! Your order successfully completed /n" +
                "Order Number:" + order.getOrderNumber() + " /n" +
                "Total price: " + order.getTotalCost() + " /n" +
                "Date: " + order.getOrderDate() + " /n"  ;

        HttpEntity<?> cusEmailEntity = prepareEmail(recipientsCustomer,customerSubject,messageCustomer,attachmentsPath);
        restTemplate.postForObject(URI_EMAIL_SERVICE + PATH_EMAIL_SEND,  cusEmailEntity, String.class);
    }

    private  void  sendEmailToVendor(String vendorEmail, Order order){
        String [] recipientsVendor = {vendorEmail};
        System.out.println("sendEmailToVendor : "+recipientsVendor[0]);
        String vendorSubject = "Your Order From eshop";
        String [] VendorAttachmentsPath = {"src/main/resources/attachments/testAttachment.txt"};
        String messageVendor = "New order! New order is placed /n" +
                "Order Number:" + order.getOrderNumber() + " /n" +
                "Total price: " + order.getTotalCost() + " /n" +
                "Date: " + order.getOrderDate() + " /n" ;

        System.out.println("The path is :"+ URI_EMAIL_SERVICE + PATH_EMAIL_SEND);
        HttpEntity<?> venEmailEntity = prepareEmail(recipientsVendor,vendorSubject,messageVendor,VendorAttachmentsPath);
        restTemplate.postForObject(URI_EMAIL_SERVICE + PATH_EMAIL_SEND,  venEmailEntity, String.class);
    }
}

