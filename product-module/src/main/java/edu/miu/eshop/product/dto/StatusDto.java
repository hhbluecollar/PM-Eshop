package edu.miu.eshop.product.dto;

import edu.miu.eshop.product.constants.ProductStatus;
import edu.miu.eshop.product.entity.Product;
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
