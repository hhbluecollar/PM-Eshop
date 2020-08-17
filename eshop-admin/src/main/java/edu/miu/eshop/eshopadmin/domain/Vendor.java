package edu.miu.eshop.eshopadmin.domain;

// EB

import edu.miu.eshop.eshopadmin.domain.Dto.BankCardDto;
import lombok.*;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "person")
@TypeAlias("vendor")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Vendor extends Person {
    private String vendorName;
    private String description;
    private String contactMethod;
    private Set<BankCardDto> cards = new HashSet<>();

    public void addCard(BankCardDto newCard){
        this.cards.add(newCard);
    }

    public void deleteCard(BankCardDto bankCardDto) {
        this.cards.remove(bankCardDto);
    }
}
