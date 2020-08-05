package edu.miu.microservice.repository;

import edu.miu.microservice.entity.search.Product;
import edu.miu.microservice.entity.shoppingcart.pattern.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String>  {

    Customer findByUserName(String userName);
    Customer findDistinctFirstByUserName(String userName);
    Customer findByEmail(String email);

}
