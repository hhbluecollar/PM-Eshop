package edu.miu.eshop.bankapi.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TransactionHist {

	@Id
	private String id;
	private Transaction transaction;
	private boolean response;
	private LocalDate date;
	private LocalTime time;

	public TransactionHist(Transaction transaction, boolean response, LocalDate date, LocalTime time) {
		this.transaction = transaction;
		this.response = response;
		this.date = date;
		this.time = time;

	}

	public Transaction getTransaction() {
		return transaction;
	}

	public boolean getResponse() {
		return response;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public void setResponse(boolean response) {
		this.response = response;
	}
	public Month getMonth() {
		return date.getMonth();
	}
	public Double getTransactionAmount() {
		return transaction.getAmount();
	}
}
