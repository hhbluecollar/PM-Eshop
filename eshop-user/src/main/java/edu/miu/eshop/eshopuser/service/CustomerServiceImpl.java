package edu.miu.eshop.eshopuser.service;

import edu.miu.eshop.eshopuser.domain.Customer;
import edu.miu.eshop.eshopuser.domain.CustomerStatus;
import edu.miu.eshop.eshopuser.domain.Dto.BankCardDto;
import edu.miu.eshop.eshopuser.domain.Dto.CustomerDto;
import edu.miu.eshop.eshopuser.exception.CustomerNotFoundException;
import edu.miu.eshop.eshopuser.exception.EmailAlreadyExistException;
import edu.miu.eshop.eshopuser.repository.CustomerRepository;
import edu.miu.eshop.eshopuser.security.request.AuthenticationRequest;
import edu.miu.eshop.eshopuser.security.response.CustomerResponse;
import edu.miu.eshop.eshopuser.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{

    private final BCryptPasswordEncoder passwordEncoder;

    private final CustomerRepository customerRepository;

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, BCryptPasswordEncoder passwordEncoder, MongoTemplate mongoTemplate) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Customer> getAll() {

        return customerRepository.findAll();
    }

    @Override
    public Customer findById(String customerId) {
        return customerRepository.findByCustomerId(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    @Override
    public Customer save(Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }

    @Override
    public void deleteById(String customerId) {
        customerRepository.findByCustomerId(customerId).map(customer -> {
            customer.setStatus(CustomerStatus.DELETED);
            return customerRepository.save(customer);
        });
    }
    @Override
    public void suspendById(String customerId) {
        customerRepository.findByCustomerId(customerId).map(customer -> {
            customer.setStatus(CustomerStatus.SUSPENDED);
            return customerRepository.save(customer);
        });
    }

    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username).orElseThrow(() -> new CustomerNotFoundException(username));
    }

    @Override
    public CustomerResponse saveNew(AuthenticationRequest authenticationRequest) throws EmailAlreadyExistException {
        isUserNameExists(authenticationRequest.getUsername());
        Customer newCustomer = new Customer();
        newCustomer.setUsername(authenticationRequest.getUsername());
        newCustomer.setPassword(encodePassword(authenticationRequest.getPassword()));
        newCustomer.setCreatedDate(LocalDate.now());
        newCustomer.setCustomerId(IdGenerator.getTimeStamp());
        customerRepository.save(newCustomer);
        Customer addedCustomer = customerRepository.findByUsername(newCustomer.getUsername()).get();
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setCustomerId(addedCustomer.getCustomerId());
        customerResponse.setUsername(addedCustomer.getUsername());
        customerResponse.setRole(addedCustomer.getRole());
        customerResponse.setStatus(addedCustomer.getStatus());
        return customerResponse;
    }

    @Override
    public Customer update(String customerId, CustomerDto newCustomer) {
        Customer customer = findById(customerId);
        customer.setFirstName(newCustomer.getFirstName());
        customer.setLastName(newCustomer.getLastName());
        customer.setPhone(newCustomer.getPhone());
        customer.setCards(newCustomer.getCards());
        customer.setStatus(newCustomer.getStatus());
        customer.setImageUrl(newCustomer.getImageUrl());
        customer.setBillingAddress(newCustomer.getBillingAddress());
        customer.setShippingAddress(newCustomer.getShippingAddress());
        return customerRepository.save(customer);
    }

    @Override
    public void removeCard(String customerId, BankCardDto bankCardDto) {
        Query query = new Query(Criteria.where("customerId").is(customerId));
        Update update = new Update().pull("cards", bankCardDto);
        Customer oldCustomer = mongoTemplate.update(Customer.class)
                .matching(query)
                .apply(update)
                .findAndModifyValue();
    }

    @Override
    public void addScore(String customerId, double score) {
        Customer customer = findById(customerId);
        customer.addScore(score);
        save(customer);
    }

    @Override
    public void resetPassword(String customerId, String password) {
        Customer customer = findById(customerId);
        customer.setPassword(encodePassword(password));
        save(customer);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private void isUserNameExists(String username) throws EmailAlreadyExistException {
        if (customerRepository.existsByUsername(username))
            throw new EmailAlreadyExistException("Email is already registered!");
    }
}
