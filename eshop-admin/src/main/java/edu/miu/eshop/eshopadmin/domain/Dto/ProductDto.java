package edu.miu.eshop.eshopadmin.domain.Dto;

// HH

import edu.miu.eshop.eshopadmin.domain.ProductDetail;
import edu.miu.eshop.eshopadmin.domain.ProductStatus;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ProductDto {
    private String productId;
    private String productName;
    private double price;
    private String description;

    private String manufacturer;
    private  int currentQuantity;
    private ArrayList<ProductDetail> productDetails;
    private String vendorId;
    private String  categoryName;
    private String  categoryId;
    private ProductStatus status;
    private List<String> imageList;
}
