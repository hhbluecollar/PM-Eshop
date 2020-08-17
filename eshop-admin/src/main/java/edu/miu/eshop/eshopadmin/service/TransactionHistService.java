package edu.miu.eshop.eshopadmin.service;

import edu.miu.eshop.eshopadmin.domain.TransactionHist;

import java.time.Month;
import java.util.List;
import java.util.Map;

public interface TransactionHistService {

    double getTotalTransaction(boolean b);

    List<TransactionHist> getAllTransactionHist();

    double getTotalTransactionAmt();

    Map<Month, Double> getTransactionByMonth(int i);
}
