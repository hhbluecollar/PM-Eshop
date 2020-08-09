package edu.miu.eshop.product.repository;

import edu.miu.eshop.product.entity.Product;
import edu.miu.eshop.product.entity.Promotion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends MongoRepository<Promotion, String> {
    List<Promotion> findByProductId(String productId);
}
