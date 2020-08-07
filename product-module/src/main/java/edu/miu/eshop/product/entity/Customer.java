package edu.miu.eshop.product.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@ToString
@Setter
@Getter
@AllArgsConstructor
@Document
@EqualsAndHashCode
public class Customer {

    @Id
    private  String id;
    @Indexed(unique=true) // make it unique username
    @NotEmpty
    private String userName;
    private String firstName;
    private String lastName;
    @Email
    private String email;

    private String phone;
    private ShoppingCart cart;

    private Address billingAddress;
    private Address shippingAddress;

    // one-to--(zero or many) unidirectional
    private List<PaymentCard> paymentCards = new ArrayList<>();

    public PaymentCard addPaymentCard(long cardNumber, String cvv, String cardHolder, LocalDate expirationDate,boolean cardStatus){
      PaymentCard pCard = new PaymentCard(cardNumber, cvv, cardHolder, expirationDate,cardStatus);
      paymentCards.add(pCard);
      return pCard;
    }

    // one-to-(zero or many) unidirectional
//    private List<Order> orders = new ArrayList<>();
//    public void addOrder(String orderNumber, LocalDate orderDate, Address shippingAddress, Address billingAddress, List<CartItem> cartItems) {
//        Order order = new Order(orderNumber,  orderDate,  shippingAddress,  billingAddress,  cartItems, this);
//        orders.add(order);
//    }

    public Customer(String userName, String firstName, String lastName, String email, String phone, Address billingAddress, Address shippingAddress) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }
}
