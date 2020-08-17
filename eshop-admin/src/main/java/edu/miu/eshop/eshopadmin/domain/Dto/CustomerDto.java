package edu.miu.eshop.eshopadmin.domain.Dto;

import edu.miu.eshop.eshopadmin.domain.Customer;
import edu.miu.eshop.eshopadmin.domain.CustomerStatus;
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
    private double getTotalScore;
    private String imageUrl;
    private AddressDto billingAddress;
    private AddressDto shippingAddress;

    public static CustomerDto build(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getUsername(),
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
