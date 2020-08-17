package edu.miu.eshop.bankapi.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.miu.eshop.bankapi.domain.PaymentMethod;

@Repository
public interface PaymentMethodRepository extends MongoRepository<PaymentMethod, String> {

	public PaymentMethod findPaymentMethodByname(String name);
}
