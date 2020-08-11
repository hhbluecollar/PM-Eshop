package edu.miu.eshop.product.dto;

import edu.miu.eshop.product.constants.CustomerStatus;
import edu.miu.eshop.product.entity.Customer;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class VendorDto {

        private String vendorName;
        private String description;
        private String contactMethod;
        private Set<BankCardDto> cards;

        public void addCard(BankCardDto newCard){
            this.cards.add(newCard);
        }

        public void deleteCard(BankCardDto bankCardDto) {
            this.cards.remove(bankCardDto);

    }
}