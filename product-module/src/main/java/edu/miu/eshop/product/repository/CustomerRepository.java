package edu.miu.eshop.product.repository;

import edu.miu.eshop.product.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String>  {

    Customer findByUserName(String userName);
    Customer findDistinctFirstByUserName(String userName);
    Customer findByEmail(String email);

}
