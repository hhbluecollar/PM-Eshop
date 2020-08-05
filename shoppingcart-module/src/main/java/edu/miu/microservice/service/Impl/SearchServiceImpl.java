package edu.miu.microservice.service.Impl;

import edu.miu.microservice.entity.search.Product;
import edu.miu.microservice.entity.search.SearchHistory;
import edu.miu.microservice.repository.SearchHistoryRepository;
import edu.miu.microservice.repository.SearchRepository;
import edu.miu.microservice.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional

public class SearchServiceImpl implements SearchService {

    @Autowired
    private  SearchRepository searchRepository;
    @Autowired
    private SearchHistoryRepository searchHistoryRepository;

    @Override
    public ArrayList<Product> findByAll(String productName, String productCategory, String manufacturer, Double minProductPrice, Double maxProductPrice) {
        return  searchRepository.findByProductNameAndProductCategoryAndManufacturerAndPriceBetween( productName,  productCategory,  manufacturer,  minProductPrice,  maxProductPrice);
    }

    @Override
    public ArrayList<Product> findByNameCategoryPrice(String productName, String productCategory, Double minProductPrice, Double maxProductPrice) {
        return  searchRepository.findByProductNameAndProductCategoryAndPriceBetween(productName, productCategory, minProductPrice, maxProductPrice);
    }

    @Override
    public ArrayList<Product> findByNameManufacturerPrice(String productName, String manufacturer, Double minProductPrice, Double maxProductPrice) {
        return  searchRepository.findByProductNameAndManufacturerAndPriceBetween(productName, manufacturer, minProductPrice, maxProductPrice);

    }

    @Override
    public ArrayList<Product> findByNameCategoryManufacturer(String productName, String productCategory, String manufacturer) {
        return  searchRepository.findByProductNameAndProductCategoryAndManufacturer(productName, productCategory, manufacturer);

    }

    @Override
    public ArrayList<Product> findByNameCategory(String productName, String productCategory) {
        return searchRepository.findByProductNameAndProductCategory( productName,  productCategory);
    }

    @Override
    public ArrayList<Product> findByNameManufacturer(String productName, String manufacturer) {
        return searchRepository.findByProductNameAndManufacturer( productName,  manufacturer);
    }

    @Override
    public ArrayList<Product> findByNamePrice(String productName, Double minProductPrice, Double maxProductPrice) {
        return searchRepository.findByProductNameAndPriceBetween( productName, minProductPrice, maxProductPrice);
    }

    @Override
    public ArrayList<Product> findByName(String productName) {
        return searchRepository.findByProductName(productName);
    }

    @Override
    public ArrayList<Product> findByCategory(String productCategory) {
        return searchRepository.findByProductCategory(productCategory);
    }

    @Override
    public ArrayList<Product> findByManufacturer(String manufacturer) {
        return searchRepository.findByManufacturer(manufacturer);
    }

    @Override
    public ArrayList<Product> findByPrice(Double minProductPrice, Double maxProductPrice) {
        return searchRepository.findByPriceIsBetween(minProductPrice, maxProductPrice);
    }

    @Override
   public ArrayList<Product> findByGeneric(String productName, String productCategory, String manufacturer, Double minProductPrice, Double maxProductPrice){
        return searchRepository.findByProductNameOrProductCategoryOrManufacturerOrPriceBetween(productName, productCategory, manufacturer, minProductPrice, maxProductPrice);
    }

    @Override
    public ArrayList<Product> findAll() {
        return searchRepository.findAll();
    }

    @Override
    public void saveSearchHistory(SearchHistory searchHistory) {
        searchHistoryRepository.save(searchHistory);
    }

    public ArrayList<Product> searchType(String productName,String productCategory,String manufacturer, Double maxProductPrice,Double minProductPrice) {

        productName = productName.trim(); //.toLowerCase();

        boolean byProductName = (productName != null && !productName.isEmpty());
        boolean byProductCategory = (productCategory != null && !productCategory.isEmpty());
        boolean byProductManufacturer = (manufacturer != null && !manufacturer.isEmpty());

        boolean byMinPrice = (minProductPrice != 0);
        boolean byMaxPrice = (maxProductPrice != 0);

        System.out.println("Product: " + byProductName);
        System.out.println("Category: " + byProductCategory);
        System.out.println("Manufacturer: " + byProductManufacturer);
        System.out.println("byMinPrice: " + byMinPrice);
        System.out.println("byMaxPrice: " + byMaxPrice);


        boolean byGeneric  =  ((productName != null && !productName.isEmpty()) || (productCategory != null && !productCategory.isEmpty())||
                            (manufacturer != null&&!manufacturer.isEmpty())||  (maxProductPrice != 0|| minProductPrice != 0));

        if ( byProductName && byProductCategory && byProductManufacturer && (byMinPrice || byMaxPrice)) {
             return findByAll(productName, productCategory, manufacturer, minProductPrice, maxProductPrice);

        } else if (byProductName && byProductCategory && (byMinPrice || byMaxPrice)) {
            return findByNameCategoryPrice(productName, productCategory, minProductPrice, maxProductPrice);

        } else if (byProductName && byProductManufacturer && (byMinPrice || byMaxPrice)) {
            return findByNameManufacturerPrice(productName, manufacturer, minProductPrice, maxProductPrice);

        } else if (byProductName && byProductCategory && byProductManufacturer ) {
            return findByNameCategoryManufacturer(productName, productCategory, manufacturer);

        } else if (byProductName && byProductCategory) {
            return findByNameCategory(productName,productCategory );

        } else if (byProductName && byProductManufacturer) {
            return findByNameManufacturer(productName, manufacturer);

        } else if (byProductName && (byMinPrice || byMaxPrice)) {
            return findByNamePrice(productName, minProductPrice, maxProductPrice);

        }else if (byProductName) {             System.out.println(productName);
            return findByName(productName);


        }else if (byProductCategory) {
            return findByCategory(productCategory);

        }else if (byProductManufacturer) {
            return findByManufacturer(manufacturer);

        }else if ((byMinPrice || byMaxPrice)) {
            return findByPrice(minProductPrice, maxProductPrice);
        }else if(byGeneric){
            return findByGeneric(productName, productCategory, manufacturer, minProductPrice, maxProductPrice);

        }else
            return findAll();
    }
}
