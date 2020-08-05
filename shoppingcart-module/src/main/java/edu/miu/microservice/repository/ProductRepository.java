package edu.miu.microservice.repository;

import edu.miu.microservice.entity.search.Product;
import edu.miu.microservice.entity.search.SearchHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>  {

    Product findByProductId(String productId);
}
