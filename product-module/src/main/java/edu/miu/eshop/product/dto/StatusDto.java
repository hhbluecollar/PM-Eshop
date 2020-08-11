package edu.miu.eshop.product.dto;

import edu.miu.eshop.product.constants.ProductStatus;
import edu.miu.eshop.product.entity.Product;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StatusDto {
    private ProductStatus status;
}
