package edu.miu.microservice.api.Controller;

import edu.miu.microservice.entity.search.Product;
import edu.miu.microservice.entity.shoppingcart.pattern.CartItem;
import edu.miu.microservice.entity.shoppingcart.pattern.Customer;
import edu.miu.microservice.entity.shoppingcart.pattern.ShoppingCart;
import edu.miu.microservice.repository.CustomerRepository;
import edu.miu.microservice.service.OrderService;
import edu.miu.microservice.service.ProductService;
import edu.miu.microservice.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService cartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RestTemplate restTemplate;

    //CREATE SHOPPING CART , ALERT!! NOT needed anymore for the current implementation
    @PostMapping("create")
    public void createCart(@RequestBody Customer customer){
         cartService.createNewCart(customer);
    }

    //ADD PRODUCT, ALSO UPDATES QUANTITY
    @GetMapping("add/{productId}/{quantity}/{userName}")
    public ShoppingCart addCartItem(@PathVariable String productId, @PathVariable int quantity, @PathVariable String userName){
        Customer customer = customerRepository.findDistinctFirstByUserName(userName);
        ShoppingCart cart = customer.getCart();
        Product product = productService.getProduct(productId);
        if(product.isActive()&&product.isAvailable()== true && product.isActive()==true )
            cartService.addCartItem(customer,product,quantity);
        else if(product.getCurrentQuantity()-quantity>=0) System.out.println("Sorry, the requested amount is not available in stock.");
        return cart;
    }

    //READ
    @GetMapping("get/{productId}/{userName}")
    public CartItem getCartItem(@PathVariable("productId") String productId ,@PathVariable String userName){
        Customer customer = customerRepository.findDistinctFirstByUserName(userName);
        System.out.println(userName);
        return customer.getCart().getCartItems().stream().filter(i->i.getProductId().equals(productId)).findFirst().orElse( null);
    }

    //UPDATE PRODUCT QUANTITY
    @PutMapping("update/{productId}/{updatedQuantity}/{userName}")
    public ShoppingCart updateQuantity(@PathVariable("productId") String productId,@PathVariable int updatedQuantity, @PathVariable String userName){
        Customer customer = customerRepository.findDistinctFirstByUserName(userName);
        cartService.setProductQuantity(customer, productId, updatedQuantity);
        ShoppingCart cart = customer.getCart();
        return  cart;
    }

    //REMOVE PRODUCT
    @DeleteMapping("delete/{productid}/{userName}")
    public ShoppingCart removeItem(@PathVariable("productid") String productId, @PathVariable String userName){
        Customer customer = customerRepository.findDistinctFirstByUserName(userName);
        cartService.removeItem(customer,productId);
        ShoppingCart cart = customer.getCart();
        return cart;
    }

    //READ
    @GetMapping("getAll/{userName}")
    public List<CartItem> getCartItems(@PathVariable String userName){
        Customer customer = customerRepository.findDistinctFirstByUserName(userName);
        return customer.getCart().getCartItems();
    }

    /**
     * For test to be deleted
     */

    @PostMapping("saveproduct")
    public Product saveProdcut(@RequestBody Product product){
        productService.save(product);
        return product;
    }

    @PostMapping("saveuser")
    public Customer saveCustomer(@RequestBody Customer customer){

        customerRepository.save(customer);
        return customer;
    }
}
