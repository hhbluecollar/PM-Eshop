package edu.miu.eshop.product.repository;

import edu.miu.eshop.product.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {

}
