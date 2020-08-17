package edu.miu.eshop.eshopuser.service;

import edu.miu.eshop.eshopuser.domain.Customer;
import edu.miu.eshop.eshopuser.domain.Dto.BankCardDto;
import edu.miu.eshop.eshopuser.domain.Dto.CustomerDto;
import edu.miu.eshop.eshopuser.exception.EmailAlreadyExistException;
import edu.miu.eshop.eshopuser.security.request.AuthenticationRequest;
import edu.miu.eshop.eshopuser.security.response.CustomerResponse;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getAll();
    Customer findById(String customerId);

    Customer save(Customer newCustomer);

    void deleteById(String customerId);

    void suspendById(String customerId);

    Customer findByUsername(String username);

    CustomerResponse saveNew(AuthenticationRequest authenticationRequest) throws EmailAlreadyExistException;

    Customer update(String customerId, CustomerDto newCustomer);

    void removeCard(String customerId, BankCardDto bankCardDto);

    void addScore(String customerId, double score);

    void resetPassword(String customerId, String password);
}
