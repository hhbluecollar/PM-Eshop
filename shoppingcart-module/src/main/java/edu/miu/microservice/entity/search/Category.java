package edu.miu.microservice.entity.search;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@ToString
@Setter
@Getter
public class Category {

    /**
     * We should ask category status ???
     */

    private String categoryName;
    private List<Category> subCategories;

    public Category(String categoryName, List<Category> subCategories) {
        this.categoryName = categoryName;
        this.subCategories = subCategories;
    }
}
