package edu.miu.eshop.mastercardbank.service;

import java.math.BigInteger;
import java.util.List;

import edu.miu.eshop.mastercardbank.domain.Mastercarddata;
import edu.miu.eshop.mastercardbank.domain.Transaction;

public interface MastercarddataService {

	public Mastercarddata findMastercarddata(BigInteger cardNumber);
	public void saveMastercarddata(Mastercarddata data );
	public List<Mastercarddata> getMastercarddata();
	public boolean confirmTransaction(Transaction transaction);
	public double getBalanceOfAccount(String cardname);
}
