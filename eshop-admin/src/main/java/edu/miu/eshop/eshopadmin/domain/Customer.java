package edu.miu.eshop.eshopadmin.domain;

import edu.miu.eshop.eshopadmin.domain.Dto.AddressDto;
import edu.miu.eshop.eshopadmin.domain.Dto.BankCardDto;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate createdDate;
    private String phone;
    private Set<BankCardDto> cards;
    private CustomerStatus status = CustomerStatus.ACTIVE;
    private CustomerRole role = CustomerRole.ROLE_CUSTOMER;
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
