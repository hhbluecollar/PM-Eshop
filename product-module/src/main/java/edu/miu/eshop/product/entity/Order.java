package edu.miu.eshop.product.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@ToString
@Setter
@Getter
@Document
public class Order {

    @Id
    private String id;
    @Indexed(unique=true) // make it unique username
    private String orderNumber;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime orderDate;
    private double totalCost;
    private List<CartItem> cartItem; // use design pattern
    private String userName;
    private String customerId;

    /**
     *  USE DESIGN PATTERN TO INITIALIZE FIELDS
     * @param orderNumber
     * @param orderDate
     * @param cartItem
     * @param orderedBy
     */
    public Order(String orderNumber, LocalDateTime orderDate, List<CartItem> cartItem, String orderedBy) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.cartItem = cartItem;
        this.userName = orderedBy;
    }
}
