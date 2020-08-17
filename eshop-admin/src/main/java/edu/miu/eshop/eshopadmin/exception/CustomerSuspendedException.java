package edu.miu.eshop.eshopadmin.exception;

public class CustomerSuspendedException extends RuntimeException {

    public CustomerSuspendedException(String id) {
        super("This user is suspended. " + id);
    }
}
