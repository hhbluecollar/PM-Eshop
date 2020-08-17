package edu.miu.eshop.eshopadmin.domain;

import lombok.ToString;

@ToString
//@JsonDeserialize(using = ProductStatusDeserializer.class)
public enum ProductStatus {
    NEW,  ACTIVE,  DOWNLOADED;
}