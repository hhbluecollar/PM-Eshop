package edu.miu.microservice.service.Impl;

import edu.miu.microservice.entity.search.Product;
import edu.miu.microservice.repository.ProductRepository;
import edu.miu.microservice.service.OrderService;
import edu.miu.microservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product getProduct(String productId) {
        return productRepository.findById(productId).get();
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }
}
