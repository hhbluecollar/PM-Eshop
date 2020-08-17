package edu.miu.eshop.bankapi.Service;

import java.util.List;

import edu.miu.eshop.bankapi.domain.PaymentMethod;

public interface PaymentMethodService {

	public void addPaymentMethod(PaymentMethod paymentmethod);

	public List<PaymentMethod> getAllPaymentMethod();

	public PaymentMethod findPaymentMethodByname(String name);
	
	public PaymentMethod findPaymentMethodById(String id);
	
	public void deletePaymentMethod(String id);

}
