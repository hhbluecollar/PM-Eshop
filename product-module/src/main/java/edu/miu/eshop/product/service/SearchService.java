package edu.miu.eshop.product.service;

import edu.miu.eshop.product.entity.Product;
import edu.miu.eshop.product.entity.SearchHistory;

import java.util.ArrayList;
import java.util.List;

public interface SearchService {

    List<Product> findByAll(String productName, String productCategory, String manufacturer, Double minProductPrice, Double maxProductPrice);

    List<Product> findByNameCategoryPrice(String productName, String productCategory, Double minProductPrice, Double maxProductPrice);

    List<Product> findByNameManufacturerPrice(String productName, String manufacturer, Double minProductPrice, Double maxProductPrice);

    List<Product> findByNameCategoryManufacturer(String productName, String productCategory, String manufacturer);

    List<Product> findByNameCategory(String productName, String productCategory);

    List<Product> findByNameManufacturer(String productName, String manufacturer);

    List<Product> findByNamePrice(String productName, Double minProductPrice, Double maxProductPrice);

    List<Product> findByName(String productName);

    List<Product> findByCategory(String productCategory);

    List<Product> findByManufacturer(String manufacturer);

    List<Product> findByPrice(Double minProductPrice, Double maxProductPrice);

    List<Product> findByGeneric(String productName, String productCategory, String manufacturer, Double minProductPrice, Double maxProductPrice);

    List<Product> findAll();

    void saveSearchHistory(SearchHistory searchHistory);

    List<Product> searchType(String productName, String productCategory, String manufacturer, Double maxProductPrice, Double minProductPrice);
}
