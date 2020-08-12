package edu.miu.eshop.product.dto;

import edu.miu.eshop.product.constants.ProductStatus;
import edu.miu.eshop.product.entity.ProductDetail;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ProductDto {

    private String productName;
    private String productId;
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
