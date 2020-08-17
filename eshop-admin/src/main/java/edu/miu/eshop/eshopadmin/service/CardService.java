package edu.miu.eshop.eshopadmin.service;

import edu.miu.eshop.eshopadmin.domain.Card;
import edu.miu.eshop.eshopadmin.domain.Transaction;

public interface CardService {
    String addNewCard(Card card);

    Card getCard(String cardnumber);

    Boolean process(Transaction transaction);
}
