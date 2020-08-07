package edu.miu.eshop.product.api.Controller;

import edu.miu.eshop.product.dto.ProductDto;
import edu.miu.eshop.product.entity.Product;
import edu.miu.eshop.product.service.CategoryService;
import edu.miu.eshop.product.service.ProductService;
import edu.miu.eshop.product.service.PromotionService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private PromotionService  promotionService;

    @PostMapping("/create")
    public ResponseEntity  saveProdcut(@RequestBody Product product){
        productService.save(product);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Product Created");
    }

    @GetMapping("/{productid}")
    public ResponseEntity  getProduct(@PathVariable("productid") String id){

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body( productService.getProduct(id));
    }

    @GetMapping("/category/{categoryid}")
    public ResponseEntity getProductByCategory(@PathVariable String categoryid){
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body( productService.getProductsByCategoryId(categoryid));

    }

    @GetMapping("/category/{categoryid}/{categoryName}")
    public ResponseEntity getProductByCategoryName(@PathVariable String categoryid, @PathVariable String categoryName){
        //return ;
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body( productService.getProductsByCategoryName(categoryid,categoryName));
    }

    @GetMapping("")
    public ResponseEntity getProduct(){
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body( productService.getAll());
    }

    @GetMapping("/vendor/{vendorid}")
    public ResponseEntity getProductByVendor(@PathVariable String vendorid){

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body( productService.getProductsByVendorId(vendorid));
    }

    @GetMapping("/promotion")
    public ResponseEntity getProductOnPromotion(){
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body( productService.getProductsOnPromotion());
    }

}
