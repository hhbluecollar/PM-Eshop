package edu.miu.microservice.entity.search;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@ToString
@Setter
@Getter
public class ProductDetail {

    private String specName;
    private String specValue;
    private String specType;

    public ProductDetail(String specName, String specValue, String specType) {
        this.specName = specName;
        this.specValue = specValue;
        this.specType = specType;
    }
}
