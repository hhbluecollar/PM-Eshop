package edu.miu.eshop.product.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@ToString
@Setter
@Getter
@Document
@AllArgsConstructor
public class Product {

    @Id
    private String productId;
    //@Indexed(unique=true) // make it unique username
    private String productName;
    private double price;
    private String description;
    private String manufacturer;
    private  int currentQuantity;
    private ArrayList<ProductDetail> productDetails = new ArrayList<>();
    private String vendorId;
    @DBRef
    private Category productCategory;
    private List<ProductImage> imageList;
    private Status status ;

    public void addProductDetail( String specName, String specValue, String specType) {
        ProductDetail detail = new ProductDetail( specName,  specValue);
        productDetails.add(detail);
    }
}
