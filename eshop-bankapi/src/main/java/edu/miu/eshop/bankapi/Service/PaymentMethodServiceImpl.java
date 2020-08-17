package edu.miu.eshop.bankapi.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.eshop.bankapi.Repository.PaymentMethodRepository;
import edu.miu.eshop.bankapi.domain.PaymentMethod;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

	@Autowired
	private PaymentMethodRepository repository;

	@Override
	public void addPaymentMethod(PaymentMethod paymentmethod) {
		repository.save(paymentmethod);

	}

	@Override
	public PaymentMethod findPaymentMethodByname(String name) {

		return repository.findPaymentMethodByname(name);

	}

	@Override
	public List<PaymentMethod> getAllPaymentMethod() {
		return repository.findAll();
	}
	
	
	@Override
	public PaymentMethod findPaymentMethodById(String id) {
		return repository.findById(id).get();
	}
	
	@Override
	public void deletePaymentMethod(String id) {
		repository.deleteById(id);
	}

}
