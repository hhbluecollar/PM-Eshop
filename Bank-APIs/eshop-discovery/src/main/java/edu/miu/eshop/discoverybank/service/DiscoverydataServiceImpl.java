package edu.miu.eshop.discoverybank.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.eshop.discoverybank.domain.Transaction;
import edu.miu.eshop.discoverybank.domain.Discoverydata;
import edu.miu.eshop.discoverybank.repository.DiscoverydataRepository;


@Service
public class DiscoverydataServiceImpl implements DiscoverydataService {

	
	@Autowired
	private DiscoverydataRepository repository;
	
	
	@Override
	public Discoverydata findDiscoverydata(BigInteger cardnumber) {
		Optional<Discoverydata> discovery = repository.findById(cardnumber);
		return discovery.orElse(null);
		
	}
	
	@Override
	public void saveDiscoverydata(Discoverydata data) {
		repository.save(data);
	}
	
	
	@Override
	public List<Discoverydata> getDiscoverydata() {
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
	Discoverydata discoverydata = findDiscoverydata(cardNumber);
	if (discoverydata == null) return false;
	//if (cvv != discoverydata.getCvv() || (cardHolder.equalsIgnoreCase(discoverydata.getCardHolder()) == false)   || (billingAddress.equalsIgnoreCase(discoverydata.getBillingAddress()) == false) || discoverydata.getStatus() == false ||amount > discoverydata.getAmount())
	if (discoverydata.getStatus() == false || amount > discoverydata.getAmount())
		return false;
	
	double amt = discoverydata.getAmount();
	amt -= amount;
	discoverydata.setAmount(amt);
	Discoverydata discover = repository.findDiscoverydataBycardHolder("GROUP 3 ACCOUNT");
	double newamt = discover.getAmount();
	newamt += amount;
	discover.setAmount(newamt);
	saveDiscoverydata(discover);
	saveDiscoverydata(discoverydata);
	
	return true;
	
}
	
	
	@Override
	public double getBalanceOfAccount(String cardname) {
		Discoverydata discover = repository.findDiscoverydataBycardHolder(cardname);
		return discover.getAmount();
	}
	
}
