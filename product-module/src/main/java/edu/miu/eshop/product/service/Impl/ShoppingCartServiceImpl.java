package edu.miu.eshop.product.service.Impl;

import edu.miu.eshop.product.repository.CustomerRepository;
import edu.miu.eshop.product.repository.ShoppingCartRepository;
import edu.miu.eshop.product.entity.Product;
import edu.miu.eshop.product.entity.CartItem;
import edu.miu.eshop.product.entity.CustShopCartFactory;
import edu.miu.eshop.product.entity.Customer;
import edu.miu.eshop.product.entity.ShoppingCart;
import edu.miu.eshop.product.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository ;
    @Autowired
    private CustomerRepository customerRepository;


    @Override

    public void createNewCart(Customer customer) {
        customerRepository.save(customer);
        ShoppingCart cart = CustShopCartFactory.createShoppingCart(customer);
        shoppingCartRepository.save(cart);
    }

    @Override
    public void addCartItem(Customer customer, Product product, int quantity) {
         List<CartItem>  cartItems =  customer.getCart().getCartItems();
         boolean exists = cartItems.stream().anyMatch(i->i.getProductId().equals(product.getProductId()));
        if(exists) {
            customer.getCart().getCartItems().stream().filter(i->i.getProductId().equals(product.getProductId())).forEach(i->{
                                                                                                                    i.setQuantity(i.getQuantity()+quantity);
                                                                                                                    });
        }else
            customer.getCart().addItem(product.getProductId(), product.getProductName(), product.getPrice(), quantity);
        customerRepository.save(customer);
    }

    @Override
    public void removeItem(Customer customer, String productId) {
        customer.getCart().getCartItems().removeIf(i->i.getProductId().equals(productId));
        customerRepository.save(customer);
    }

    @Override
    public void setProductQuantity(Customer customer, String productId, int updatedQuantity) {
        customer.getCart().getCartItems().stream().filter(i->i.getProductId().equals(productId)).forEach(i->i.setQuantity(updatedQuantity));
        customerRepository.save(customer);
    }

    @Override
    public List<CartItem> getItems(String cartId) {
        return shoppingCartRepository.findById(cartId).get().getCartItems();
    }
}
