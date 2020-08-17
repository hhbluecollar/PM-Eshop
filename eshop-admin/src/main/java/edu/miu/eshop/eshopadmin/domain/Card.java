package edu.miu.eshop.eshopadmin.domain;

import edu.miu.eshop.eshopadmin.domain.Dto.AddressDto;
import edu.miu.eshop.eshopadmin.domain.Dto.BankCardDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Card {
    private String cardNumber;
    private int cvv;
    private String cardHolder;
    private int month;
    private int year;
    private String billingAddress;

    public static Card build(BankCardDto bankCardDto, AddressDto addressDto){
        return new Card(
                bankCardDto.getCardNumber(),
                Integer.parseInt(bankCardDto.getCvv()),
                bankCardDto.getHolderName(),
                bankCardDto.getExpirationDate().getMonth().getValue(),
                bankCardDto.getExpirationDate().getYear(),
                addressDto.getStreet() + " " +  addressDto.getCity() + " " +  addressDto.getState() + " " +  addressDto.getZip());
    }
}
