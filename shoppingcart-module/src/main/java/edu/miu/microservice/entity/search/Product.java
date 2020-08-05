package edu.miu.microservice.entity.search;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;

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
    //private String productId;
    private String productName;
    private double price;
    private String description;
    private boolean isActive = true;
    private boolean isDeleted = false;
    private boolean isAvailable = true;
    private boolean isOnPromotion = false;

    private String manufacturer;
    private  int currentQuantity;
    private ArrayList<ProductDetail> productDetail = new ArrayList<>();
    private int initialQuantity;
    private Vendor vendor;
    private Category productCategory;

    //=============== PROMOTION =============
    private  String promoName;
    private String PromotionDescription;
    private  long promotionPercentage;
    private LocalDate startDate;
    private LocalDate endDate;

    public void addProductDetail( String specName, String specValue, String specType) {
        ProductDetail detail = new ProductDetail( specName,  specValue,  specType);
        productDetail.add(detail);
    }
}
