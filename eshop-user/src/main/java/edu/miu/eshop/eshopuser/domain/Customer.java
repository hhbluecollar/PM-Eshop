package edu.miu.eshop.eshopuser.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.miu.eshop.eshopuser.domain.Dto.AddressDto;
import edu.miu.eshop.eshopuser.domain.Dto.BankCardDto;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Document
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
    @Indexed(unique=true)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate createdDate;
    private String phone;
    private Set<BankCardDto> cards = new HashSet<>();
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
