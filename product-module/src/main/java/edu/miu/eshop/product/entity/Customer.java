package edu.miu.eshop.product.entity;

import edu.miu.eshop.product.constants.CustomerStatus;
import edu.miu.eshop.product.constants.Role;
import edu.miu.eshop.product.dto.AddressDto;
import edu.miu.eshop.product.dto.BankCardDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {
    @Id
    private String id;
    @Indexed(unique=true)
    private String customerId;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;
    private String phone;
    private Set<BankCardDto> cards;
    private CustomerStatus status = CustomerStatus.ACTIVE;
    private Role role = Role.ROLE_CUSTOMER;
    private String imageUrl;
    private double totalScore = 0.0;
    private AddressDto billingAddress;
    private AddressDto shippingAddress;

    public void addScore(double score){
        this.totalScore += score;
    }
    public void addCard(BankCardDto newCard){
        this.cards.add(newCard);
    }

    public void deleteCard(BankCardDto bankCardDto) {
        this.cards.remove(bankCardDto);
    }
}