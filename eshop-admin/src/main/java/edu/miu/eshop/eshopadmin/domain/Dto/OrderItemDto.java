package edu.miu.eshop.eshopadmin.domain.Dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class OrderItemDto {
    private LocalDateTime orderDate;
    private String userName;
    private String customerId;
    private String productId;
    private double price;
    private int quantity;
    private String vendorId;
}