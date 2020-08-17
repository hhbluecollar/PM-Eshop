package edu.miu.eshop.eshopuser.domain.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BankCardDto {
    private String cardNumber;
    private String holderName;
    private String bankName;
    private LocalDate expirationDate;
    private String cvv;
    private boolean cardStatus = true;
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof BankCardDto))
            return false;
        BankCardDto b = (BankCardDto) o;
        if(b.getCardNumber() != this.cardNumber)
            return false;
        return true;
    }
    public int hashCode(){
        return Objects.hash(this.cardNumber);
    }
}
