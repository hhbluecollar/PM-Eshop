package edu.miu.eshop.product.dto;

import edu.miu.eshop.product.constants.CustomerStatus;
import edu.miu.eshop.product.entity.Customer;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class CustomerDto {
    private String customerId;
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private Set<BankCardDto> cards;
    private CustomerStatus status;
    private double totalScore;
    private String imageUrl;
    private AddressDto billingAddress;
    private AddressDto shippingAddress;

    public static CustomerDto build(Customer customer) {
        return new CustomerDto(
                customer.getCustomerId(),
                customer.getUserName(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPhone(),
                customer.getCards(),
                customer.getStatus(),
                customer.getTotalScore(),
                customer.getImageUrl(),
                customer.getBillingAddress(),
                customer.getShippingAddress());
    }
}