package edu.miu.eshop.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
public class CategoryDto {

    private String categoryId;
    private String parentId;
    private String value;
}
