package edu.miu.eshop.visabank.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.eshop.visabank.domain.Visadata;
import edu.miu.eshop.visabank.domain.Transaction;
import edu.miu.eshop.visabank.repository.VisadataRepository;


@Service
public class VisadataServiceImpl implements VisadataService {
	
	@Autowired
	private VisadataRepository repository;
	
	@Override
	public Visadata findVisadata(String cardnumber) {
		Optional<Visadata> visa = repository.findById(cardnumber);
		return visa.orElse(null);
	}
	
	@Override
	public void saveVisadata(Visadata data) {
		repository.save(data);
	}
	
	@Override
	public List<Visadata> getVisadata() {
		return repository.findAll();
	}
	
	@Override
	public boolean confirmTransaction(Transaction transaction) {
		
	String cardNumber = transaction.getCard().getCardNumber();
	int cvv = transaction.getCard().getCvv();
	String cardHolder = transaction.getCard().getCardHolder();
	int month = transaction.getCard().getMonth();
	int year = transaction.getCard().getYear();
	String billingAddress = transaction.getCard().getBillingAddress();
	double amount = transaction.getAmount();
	LocalDate ld = LocalDate.now();
	if (year < ld.getYear() || (year == ld.getYear() && month < ld.getMonthValue())) return false;
	Visadata visadata = findVisadata(cardNumber);
	if (visadata == null) return false;
	visadata.setBillingAddress(billingAddress);
	visadata.setCardHolder(cardHolder);
	//if (cvv != visadata.getCvv() || (cardHolder.equalsIgnoreCase(visadata.getCardHolder()) == false)   || (billingAddress.equalsIgnoreCase(visadata.getBillingAddress()) == false) || visadata.getStatus() == false || amount > visadata.getAmount())
	if (visadata.getStatus() == false || amount > visadata.getAmount())
		return false;
	
	double amt = visadata.getAmount();
	amt -= amount;
	visadata.setAmount(amt);
	saveVisadata(visadata);
	
	return true;
}
	
}
