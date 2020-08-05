package edu.miu.microservice.service;


import edu.miu.microservice.entity.search.Product;

public interface ProductService {
    Product getProduct(String productId);
    void save(Product product);
}
