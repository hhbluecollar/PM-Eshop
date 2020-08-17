package edu.miu.eshop.bankapi.Service;

import java.math.BigInteger;
import java.util.List;



import edu.miu.eshop.bankapi.domain.Card;


public interface CardService {
	
	public void addCard(Card card);
	public Card getCard(String cardnumber);
	public List<Card> getAllCards();
	public boolean validateDate(int year, int month);
	public boolean validateCVV(int cvv);
	public String getCardType(String cardnumber);
	public String addNewCard(Card card);

	
}
