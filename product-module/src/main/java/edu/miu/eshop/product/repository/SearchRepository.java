package edu.miu.eshop.product.repository;

import edu.miu.eshop.product.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface  SearchRepository  extends MongoRepository<Product, String>  {

    ArrayList<Product> findByProductNameMatchesAndCategoryNameAndManufacturerAndPriceBetween(String productName, String productCategory, String manufacturer, Double minProductPrice, Double maxProductPrice);

    ArrayList<Product> findByProductNameMatchesAndCategoryNameAndPriceBetween(String productName, String productCategory, Double minProductPrice, Double maxProductPrice);

    ArrayList<Product> findByProductNameMatchesAndManufacturerAndPriceBetween(String productName, String manufacturer, Double minProductPrice, Double maxProductPrice);

    ArrayList<Product> findByProductNameMatchesAndCategoryNameAndManufacturer(String productName, String productCategory, String manufacturer);

    ArrayList<Product> findByProductNameMatchesAndCategoryName(String productName, String productCategory);

    ArrayList<Product> findByProductNameMatchesAndManufacturer(String productName, String manufacturer);

    ArrayList<Product> findByProductNameMatchesAndPriceBetween(String productName, Double minProductPrice, Double maxProductPrice);

    ArrayList<Product> findByProductNameMatches(String productName);

    ArrayList<Product> findByCategoryName(String productCategory);

    ArrayList<Product> findByManufacturer(String manufacturer);

    ArrayList<Product> findByPriceIsBetween(Double minProductPrice, Double maxProductPrice);

    //GENERIC CAN BE USED FOR ANY PARAMETER TYPE NEEDED ADDITIONALLY
    ArrayList<Product> findByProductNameMatchesOrCategoryNameOrManufacturerOrPriceBetween(String productName, String productCategory, String manufacturer, Double minProductPrice, Double maxProductPrice);

    ArrayList<Product> findAll();

}
