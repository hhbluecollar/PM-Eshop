package edu.miu.eshop.eshopadmin.domain.Dto;

// EB

import edu.miu.eshop.eshopadmin.domain.PersonStatus;
import edu.miu.eshop.eshopadmin.domain.Role;
import edu.miu.eshop.eshopadmin.domain.Vendor;
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
    private PersonStatus status;
    private Role role;
    private String imageUrl;
    private AddressDto address;
    private LocalDate createdDate;
    private String description;
    private String contactMethod;

    public static VendorDto build(Vendor vendor) {
        return new VendorDto(
                vendor.getPersonId(),
                vendor.getUsername(),
                vendor.getVendorName(),
                vendor.getPhone(),
                vendor.getCards(),
                vendor.getStatus(),
                vendor.getRole(),
                vendor.getImageUrl(),
                vendor.getAddress(),
                vendor.getCreatedDate(),
                vendor.getDescription(),
                vendor.getContactMethod());
    }

}