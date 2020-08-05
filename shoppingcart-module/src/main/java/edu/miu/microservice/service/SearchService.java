package edu.miu.microservice.service;

import edu.miu.microservice.entity.search.Product;
import edu.miu.microservice.entity.search.SearchHistory;

import java.util.ArrayList;
import java.util.List;

public interface SearchService {

    ArrayList<Product> findByAll(String productName, String productCategory, String manufacturer, Double minProductPrice, Double maxProductPrice);

    ArrayList<Product> findByNameCategoryPrice(String productName, String productCategory, Double minProductPrice, Double maxProductPrice);

    ArrayList<Product> findByNameManufacturerPrice(String productName, String manufacturer, Double minProductPrice, Double maxProductPrice);

    ArrayList<Product> findByNameCategoryManufacturer(String productName, String productCategory, String manufacturer);

    ArrayList<Product> findByNameCategory(String productName, String productCategory);

    ArrayList<Product> findByNameManufacturer(String productName, String manufacturer);

    ArrayList<Product> findByNamePrice(String productName, Double minProductPrice, Double maxProductPrice);

    ArrayList<Product> findByName(String productName);

    ArrayList<Product> findByCategory(String productCategory);

    ArrayList<Product> findByManufacturer(String manufacturer);

    ArrayList<Product> findByPrice(Double minProductPrice, Double maxProductPrice);

    ArrayList<Product> findByGeneric(String productName, String productCategory, String manufacturer, Double minProductPrice, Double maxProductPrice);

    ArrayList<Product> findAll();

    void saveSearchHistory(SearchHistory searchHistory);

    ArrayList<Product> searchType(String productName, String productCategory, String manufacturer, Double maxProductPrice, Double minProductPrice);
}
