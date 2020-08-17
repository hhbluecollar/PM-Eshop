package edu.miu.eshop.eshopadmin.domain.Dto;

import edu.miu.eshop.eshopadmin.domain.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusDto {
    private ProductStatus status;
}
