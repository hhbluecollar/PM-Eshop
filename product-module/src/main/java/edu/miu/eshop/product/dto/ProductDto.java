package edu.miu.eshop.product.dto;

import edu.miu.eshop.product.constants.ProductStatus;
import edu.miu.eshop.product.entity.ProductDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
