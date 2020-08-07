package edu.miu.eshop.product.service;


import edu.miu.eshop.product.dto.ProductDto;
import edu.miu.eshop.product.entity.Product;

import java.util.List;

public interface ProductService {
    ProductDto getProduct(String productId);
    void save(Product product);
    List<ProductDto> getProductsByCategoryId(String categoryId);

    List<ProductDto> getAll();

    List<ProductDto> getProductsByCategoryName( String categoryid, String categoryName);

    List<ProductDto> getProductsByVendorId(String vendorid);

    List<ProductDto> getProductsOnPromotion();
}
