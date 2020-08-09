package edu.miu.eshop.product.constants;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.ToString;

@ToString
//@JsonDeserialize(using = ProductStatusDeserializer.class)
public enum ProductStatus {

    NEW,  ACTIVE,  DOWNLOADED;
//
//    private String state;
//
//    public String getState() {
//        return state;
//    }
//
//    ProductStatus(String state) {
//        this.state = state;
//    }
}