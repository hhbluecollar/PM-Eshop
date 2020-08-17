package edu.miu.eshop.eshopadmin.domain.Dto;

// EB

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressDto {
    private String street;
    private String city;
    private String state;
    private String zip;
}
