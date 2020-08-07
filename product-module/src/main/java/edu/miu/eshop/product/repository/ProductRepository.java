package edu.miu.eshop.product.repository;

import edu.miu.eshop.product.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>  {

    Product findByProductId(String productId);
    List<Product> findByProductCategory_CategoryName(String categoryName);
    List<Product> findByProductCategory_Id(String categoryId);
    List<Product> findByVendorId(String vendorId);

}
