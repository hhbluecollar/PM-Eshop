package edu.miu.microservice.repository;

import edu.miu.microservice.entity.search.Product;
import edu.miu.microservice.entity.search.SearchHistory;
import edu.miu.microservice.entity.shoppingcart.Order;
import edu.miu.microservice.entity.shoppingcart.pattern.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String>  {

    Order findByOrderNumber(String orderNumber);

    void deleteByOrderNumber(String orderNumber);

    List<Order> findAllByOrderedBy(Customer customer);
}
