package edu.miu.eshop.product.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class CategoryDto {

    private String categoryId;
    private String parentId;
    private String value;
}
