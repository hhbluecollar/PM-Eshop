package edu.miu.eshop.bankapi.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@ToString
@Document
public class Transaction {
	private Card card;
	private double amount;
}
