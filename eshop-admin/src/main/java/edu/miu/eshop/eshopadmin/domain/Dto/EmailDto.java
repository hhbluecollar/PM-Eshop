package edu.miu.eshop.eshopadmin.domain.Dto;

import edu.miu.eshop.eshopadmin.domain.Vendor;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailDto {
    private String vendorId;
    private String email;

    public static EmailDto build(Vendor vendor){
        return new EmailDto(
                vendor.getPersonId(),
                vendor.getUsername()
        );
    }
}
