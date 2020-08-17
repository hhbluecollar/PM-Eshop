package edu.miu.eshop.visabank.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Card {
	@Id
	private String cardNumber;
	private int cvv;
	private String cardHolder;
	private int month;
	private int year;
	private String billingAddress;
}
