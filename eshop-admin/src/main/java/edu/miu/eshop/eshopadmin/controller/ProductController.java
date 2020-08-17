package edu.miu.eshop.eshopadmin.controller;

// HH

import edu.miu.eshop.eshopadmin.domain.Dto.ProductDto;
import edu.miu.eshop.eshopadmin.domain.Dto.StatusDto;
import edu.miu.eshop.eshopadmin.domain.Product;
import edu.miu.eshop.eshopadmin.domain.ProductStatus;
import edu.miu.eshop.eshopadmin.service.ProductService;
import edu.miu.eshop.eshopadmin.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders="*")
@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private PromotionService  promotionService;

	@PostMapping("/create")
	public ResponseEntity  saveProduct(@RequestBody Product product){
		productService.save(product);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(product);
	}

	@GetMapping("/{productid}")
	public ResponseEntity  getProduct(@PathVariable("productid") String id){

		return ResponseEntity
				.status(HttpStatus.OK)
				.body( productService.getProduct(id));
	}

	@GetMapping("/category/{categoryid}")
	public ResponseEntity getProductByCategory(@PathVariable String categoryid){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body( productService.getProductsByCategoryId(categoryid));
	}

	@GetMapping("/category/{categoryid}/{categoryName}")
	public ResponseEntity getProductByCategoryName(@PathVariable String categoryid, @PathVariable String categoryName){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body( productService.getProductsByCategoryName(categoryid,categoryName));
	}

	@GetMapping("")
	public ResponseEntity getProducts(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body( productService.getAll());
	}

	@GetMapping("/vendor/{vendorid}")
	public ResponseEntity getProductByVendor(@PathVariable String vendorid){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body( productService.getProductsByVendorId(vendorid));
	}

	@GetMapping("/promotion")
	public ResponseEntity getProductOnPromotion(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body( productService.getProductsOnPromotion());
	}

	@PutMapping("/updatestatus/{id}")
	public ResponseEntity updateStatus(@PathVariable String id, @RequestBody StatusDto newStatus){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(productService.updateStatus(id, newStatus).getBody().substring(14));
	}

	@PutMapping("/update/{productId}")
	public ResponseEntity updateProduct(@PathVariable String productId, @RequestBody ProductDto productDto) throws ParseException {
		ProductDto updatedProduct  = productService.updateProduct(productId, productDto).getBody();
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(updatedProduct);
	}
}