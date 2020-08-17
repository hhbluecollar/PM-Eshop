package edu.miu.eshop.eshopuser.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String id) {
        super("Could not find customer " + id);
    }
}
