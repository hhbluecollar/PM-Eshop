package edu.miu.eshop.amexbank.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Card {
    @Id
    private String cardNumber;
    private int cvv;
    private String cardHolder;
    private int month;
    private int year;
    private String billingAddress;
}
