package edu.miu.eshop.eshopuser.repository;

import edu.miu.eshop.eshopuser.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Optional<Customer> findByUsername(String username);

    boolean existsByUsername(String username);

    Optional<Customer> findByCustomerId(String customerId);
}
