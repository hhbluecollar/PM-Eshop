package edu.miu.microservice.repository;

import edu.miu.microservice.entity.shoppingcart.pattern.CartItem;
import edu.miu.microservice.entity.shoppingcart.pattern.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String> {

}
