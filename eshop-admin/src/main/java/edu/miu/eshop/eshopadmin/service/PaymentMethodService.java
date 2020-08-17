package edu.miu.eshop.eshopadmin.service;

import edu.miu.eshop.eshopadmin.domain.PaymentMethod;

import java.util.List;

public interface PaymentMethodService {

    PaymentMethod findPaymentMethodById(String id);

    void deletePaymentMethod(String id);

    List<PaymentMethod> getAllPaymentMethod();

    void addPaymentMethod(PaymentMethod paymentmethod);
}
