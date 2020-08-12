package edu.miu.eshop.product.repository;

import edu.miu.eshop.product.entity.Order;
import edu.miu.eshop.product.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String>  {

    Order findByOrderNumber(String orderNumber);

    void deleteByOrderNumber(String orderNumber);

    List<Order> findAllByCustomerId(String customerId);
}
