package edu.miu.eshop.product.repository;

import edu.miu.eshop.product.entity.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String> {

    ShoppingCart findByUserName(String userName);

    ShoppingCart findByCartItems_ProductId(String productid);
}
