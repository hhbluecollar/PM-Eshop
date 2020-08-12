package edu.miu.eshop.product.api.Controller;

import edu.miu.eshop.product.dto.ProductDto;
import edu.miu.eshop.product.dto.StatusDto;
import edu.miu.eshop.product.entity.Product;
import edu.miu.eshop.product.service.ProductService;
import edu.miu.eshop.product.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
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
                .status(HttpStatus.OK)
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

    @PutMapping("updatestatus/{id}")
    public ResponseEntity updateStatus(@PathVariable String id, @RequestBody StatusDto newstatus){
        System.out.println(newstatus);
        Product product = productService.updateStatus(id, newstatus.getStatus());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(product.getStatus().ordinal());
    }

    @DeleteMapping("/delete/{productid}")
    public ResponseEntity deleteProduct(@PathVariable String productid) {

        ProductDto deletedProduct  = productService.getProduct(productid);
        productService.deleteProduct(  productid);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body( deletedProduct);
    }

    @PutMapping("/update/{productid}")
    public ResponseEntity updateProduct(@PathVariable String productid, @RequestBody ProductDto productDto) throws ParseException {
        Product updatedProduct  = productService.updateProduct(productDto,  productid);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body( updatedProduct);
    }
}
