package edu.miu.eshop.product.repository;

import edu.miu.eshop.product.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface  SearchRepository  extends MongoRepository<Product, String>  {

    ArrayList<Product> findByProductNameAndProductCategoryAndManufacturerAndPriceBetween(String productName, String productCategory, String manufacturer, Double minProductPrice, Double maxProductPrice);

    ArrayList<Product> findByProductNameAndProductCategoryAndPriceBetween(String productName, String productCategory, Double minProductPrice, Double maxProductPrice);

    ArrayList<Product> findByProductNameAndManufacturerAndPriceBetween(String productName, String manufacturer, Double minProductPrice, Double maxProductPrice);

    ArrayList<Product> findByProductNameAndProductCategoryAndManufacturer(String productName, String productCategory, String manufacturer);

    ArrayList<Product> findByProductNameAndProductCategory(String productName, String productCategory);

    ArrayList<Product> findByProductNameAndManufacturer(String productName, String manufacturer);

    ArrayList<Product> findByProductNameAndPriceBetween(String productName, Double minProductPrice, Double maxProductPrice);

    ArrayList<Product> findByProductName(String productName);

    ArrayList<Product> findByProductCategory(String productCategory);

    ArrayList<Product> findByManufacturer(String manufacturer);

    ArrayList<Product> findByPriceIsBetween(Double minProductPrice, Double maxProductPrice);

    //GENERIC CAN BE USED FOR ANY PARAMETER TYPE NEEDED ADDITIONALLY
    ArrayList<Product> findByProductNameOrProductCategoryOrManufacturerOrPriceBetween(String productName, String productCategory, String manufacturer, Double minProductPrice, Double maxProductPrice);

    ArrayList<Product> findAll();

}
