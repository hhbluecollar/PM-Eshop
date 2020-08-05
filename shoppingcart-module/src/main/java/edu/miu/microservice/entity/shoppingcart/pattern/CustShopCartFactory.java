package edu.miu.microservice.entity.shoppingcart.pattern;

import edu.miu.microservice.entity.shoppingcart.Address;

public class CustShopCartFactory {
    public static Customer createCustomer(String userName, String firstName, String lastName, String email, String phone, Address billing, Address shipping) {
        if(userName == null)
            throw new IllegalArgumentException("Customer userName cannot be null");
        Customer cust = new Customer( userName,  firstName,  lastName,  email,  phone, billing, shipping);

        //customer set in cart
        ShoppingCart cart = new ShoppingCart();

        //cart set in customer
        cust.setCart(cart);

        return cust;
    }

    public static ShoppingCart createShoppingCart(Customer cust) {
        if(cust == null)
            throw new IllegalArgumentException("Cannot create shopping cart with null Customer");
        ShoppingCart cart = new ShoppingCart();
        //replace the cart that is currently stored in cust
        cust.setCart(cart);
        return cart;
    }

}
