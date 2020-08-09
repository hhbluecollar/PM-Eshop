package edu.miu.eshop.product.repository;

import edu.miu.eshop.product.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
    Category findByCategoryName(String categoryName);

}
