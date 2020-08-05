package edu.miu.microservice.entity.shoppingcart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
@NoArgsConstructor
@ToString
@Setter
@Getter
public class PaymentCard {

    private long cardNumber;
    private String  cvv;
    private String cardHolder;
    private LocalDate  expirationDate;
    private boolean cardStatus;

    public PaymentCard(long cardNumber, String cvv, String cardHolder, LocalDate expirationDate, boolean cardStatus) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.cardHolder = cardHolder;
        this.expirationDate = expirationDate;
        this.cardStatus = true;
    }
}
