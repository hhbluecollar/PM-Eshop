package edu.miu.eshop.product.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@ToString
@Setter
@Getter
@AllArgsConstructor

public class Card {

	private String cardNumber;
	private int cvv;
	private String cardHolder;
	private int month;
	private int year;
	private String billingAddress;
}