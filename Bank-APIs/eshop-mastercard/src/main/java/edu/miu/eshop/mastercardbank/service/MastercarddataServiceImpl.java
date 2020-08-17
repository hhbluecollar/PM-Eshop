package edu.miu.eshop.mastercardbank.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.eshop.mastercardbank.domain.Mastercarddata;
import edu.miu.eshop.mastercardbank.domain.Transaction;
import edu.miu.eshop.mastercardbank.repository.MastercarddataRepository;


@Service
public class MastercarddataServiceImpl implements MastercarddataService {

	
	@Autowired
	private MastercarddataRepository repository;
	
	
	@Override
	public Mastercarddata findMastercarddata(BigInteger cardnumber) {
		Optional<Mastercarddata> mastercard = repository.findById(cardnumber);
		return mastercard.orElse(null);
		
	}
	
	@Override
	public void saveMastercarddata(Mastercarddata data) {
		repository.save(data);
	}
	
	
	@Override
	public List<Mastercarddata> getMastercarddata() {
		return repository.findAll();
	}
	
	@Override
	public boolean confirmTransaction(Transaction transaction) {
	BigInteger cardNumber = transaction.getCard().getCardNumber();
	int cvv = transaction.getCard().getCvv();
	String cardHolder = transaction.getCard().getCardHolder();
	int month = transaction.getCard().getMonth();
	int year = transaction.getCard().getYear();
	String billingAddress = transaction.getCard().getBillingAddress();
	double amount = transaction.getAmount();
	LocalDate ld = LocalDate.now();
	if (year < ld.getYear() || (year == ld.getYear() && month < ld.getMonthValue())) return false;
	Mastercarddata mastercarddata = findMastercarddata(cardNumber);
	if (mastercarddata == null) return false;
	if (cvv != mastercarddata.getCvv() || (cardHolder.equalsIgnoreCase(mastercarddata.getCardHolder()) == false)   || (billingAddress.equalsIgnoreCase(mastercarddata.getBillingAddress()) == false) || mastercarddata.getStatus() == false ||amount > mastercarddata.getAmount())
	return false;
	
	double amt = mastercarddata.getAmount();
	amt = amt - amount;
	mastercarddata.setAmount(amt);
	Mastercarddata master = repository.findMastercarddataBycardHolder("GROUP 3 ACCOUNT");
	double newamt = master.getAmount();
	newamt += amount;
	master.setAmount(newamt);
	saveMastercarddata(master);
	saveMastercarddata(mastercarddata);
	
	return true;
	
}
	

	@Override
	public double getBalanceOfAccount(String cardname) {
		Mastercarddata master = repository.findMastercarddataBycardHolder(cardname);
		return master.getAmount();
	}
	
}
