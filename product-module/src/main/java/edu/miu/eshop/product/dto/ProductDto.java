package edu.miu.eshop.product.dto;

import edu.miu.eshop.product.entity.Category;
import edu.miu.eshop.product.entity.ProductDetail;
import edu.miu.eshop.product.entity.ProductImage;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

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
    private Category productCategory;

    private List<ProductImage> imageList;
}
