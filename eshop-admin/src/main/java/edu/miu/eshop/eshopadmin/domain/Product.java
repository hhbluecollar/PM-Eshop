package edu.miu.eshop.eshopadmin.domain;

// HH

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
@AllArgsConstructor
public class Product {
	private String productId;
	private String productName;
	private double price;
	private String description;
	private String manufacturer;
	private int currentQuantity;
	private ArrayList<ProductDetail> productDetails = new ArrayList<>();
	private String vendorId;
	private String  categoryName;
	private String  categoryId;
	private List<String> imageList;
	private ProductStatus status ;

	public void addProductDetail( String specName, String specValue, String specType) {
		ProductDetail detail = new ProductDetail( specName,  specValue);
		productDetails.add(detail);
	}
}