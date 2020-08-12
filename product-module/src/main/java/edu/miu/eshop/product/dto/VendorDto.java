package edu.miu.eshop.product.dto;

import edu.miu.eshop.product.constants.CustomerStatus;
import edu.miu.eshop.product.constants.Role;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VendorDto {
    private String vendorId;
    private String username;
    private String vendorName;
    private String phone;
    private Set<BankCardDto> cards;
    private CustomerStatus status;
    private Role role;
    private String imageUrl;
    private AddressDto address;
    private LocalDate createdDate;
    private String description;
    private String contactMethod;
}