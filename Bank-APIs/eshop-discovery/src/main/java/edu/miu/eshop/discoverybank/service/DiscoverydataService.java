package edu.miu.eshop.discoverybank.service;

import java.math.BigInteger;
import java.util.List;

import edu.miu.eshop.discoverybank.domain.Transaction;
import edu.miu.eshop.discoverybank.domain.Discoverydata;

public interface DiscoverydataService {

	public Discoverydata findDiscoverydata(BigInteger cardNumber);
	public void saveDiscoverydata(Discoverydata data );
	public List<Discoverydata> getDiscoverydata();
	public boolean confirmTransaction(Transaction transaction);
	public double getBalanceOfAccount(String cardname);
		
}
