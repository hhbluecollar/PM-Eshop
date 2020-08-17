package edu.miu.eshop.bankapi.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class Card {
	@Id
	private String cardNumber;
	private int cvv;
	private String cardHolder;
	private int month;
	private int year;
	private String billingAddress;

	public Card(String cardnumber, int cvv, String cardholder, String address, int month, int year) {
		this.cardNumber = cardnumber;
		this.cvv = cvv;
		this.cardHolder = cardholder;
		this.month = month;
		this.year = year;
		this.billingAddress = address;
	}
}
