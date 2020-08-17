package edu.miu.eshop.product.api.Controller;

import edu.miu.eshop.product.dto.CartItemDto;
import edu.miu.eshop.product.dto.ProductDto;
import edu.miu.eshop.product.dto.ShoppingCartDto;
import edu.miu.eshop.product.entity.*;
import edu.miu.eshop.product.service.ProductService;
import edu.miu.eshop.product.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/create")
    public ResponseEntity  createCart(@RequestBody  ShoppingCart shoppingCart){

        String user = shoppingCart.getUserName();

        //CREATE THE CART
        ShoppingCart cart = shoppingCartService.createNewCart(user);

        //ADD TO THE CART
        for (CartItem item:shoppingCart.getCartItems()) {

            ProductDto product = productService.getProduct(item.getProductId());

            if (product == null) ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
            shoppingCartService.addCartItem(product, cart, item.getQuantity());
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Cart created");
    }

    //ADD PRODUCT, ALSO UPDATES QUANTITY
    @PostMapping("cartitem/add")
    public ResponseEntity addCartItem(@RequestBody CartItemDto cartItemDto){
        ShoppingCart cart = shoppingCartService.findCartForUser(cartItemDto.getUserName());
        ProductDto product  = productService.getProduct(cartItemDto.getProductId());
        if(product==null)  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        //boolean exists = cart.getCartItems().stream().anyMatch(i->i.getProductId().equals(cartItemDto.getProductId()));
        if(cart!=null&&product!=null){

                    shoppingCartService.addCartItem(product, cart, cartItemDto.getQuantity());
        }else{
           return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Cart not found");
        }
        return   ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cart);
    }

    //READ
    @GetMapping("/cartitem/{productid}/{userName}")
    public ResponseEntity getCartItem(@PathVariable("productid") String productid , @PathVariable String userName){
        ShoppingCart cart  = shoppingCartService.findCartForUser(userName);
        if(cart==null) ResponseEntity.status(HttpStatus.NOT_FOUND). body("Cart not found");
        //if(productService.getProduct(productid)==null) ResponseEntity.status(HttpStatus.NOT_FOUND). body("Product not found");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cart.getCartItems().stream().filter(i->i.getProductId().equals(productid)).findFirst().orElse( null));
    }

    //UPDATE PRODUCT QUANTITY
    @PutMapping("cartitem/updatequantity/{productid}/{quantity}")
    public ResponseEntity updateQuantity(@RequestBody ShoppingCartDto shoppingCartDto, @PathVariable String productid, @PathVariable int quantity){
        ShoppingCart cart  = shoppingCartService.findCartForUser(shoppingCartDto.getUserName());
        shoppingCartService.setProductQuantity(cart, productid, quantity);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(cart);
    }

    //REMOVE PRODUCT
    @DeleteMapping("/cartitem/delete")
    public ResponseEntity removeItem(@RequestBody ShoppingCartDto shoppingCartDto){
        ShoppingCart cart = shoppingCartService.findCartForUser(shoppingCartDto.getUserName());
        shoppingCartService.removeItem(cart,shoppingCartDto.getCartItem().getProductId());
        return  ResponseEntity
                    .status(HttpStatus.OK)
                    .body(cart.getCartItems());
    }

    //READ
    @GetMapping("/{userName}")
    public ResponseEntity getCartItems(@PathVariable String userName){
        ShoppingCart cart = shoppingCartService.findCartForUser(userName);
        if(cart==null)
            return  ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Cart is empty");
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(cart.getCartItems());
    }
}
