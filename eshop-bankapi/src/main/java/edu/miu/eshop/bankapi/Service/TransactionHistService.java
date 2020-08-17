package edu.miu.eshop.bankapi.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.Map;

import edu.miu.eshop.bankapi.domain.Transaction;
import edu.miu.eshop.bankapi.domain.TransactionHist;

public interface TransactionHistService {

	void saveTransactionHist(Transaction transaction, boolean response, LocalDate date, LocalTime time);
	
	List<TransactionHist> getAllTransactionHist();
	double getTotalTransaction(boolean response);

    double getTotalTransactionAmt();

    Map<Month, Double> getTransactionByMonth(int i);
}
