package edu.miu.eshop.product.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@ToString
@Setter
@Getter
@Document
@AllArgsConstructor

public class Address {

    @Id
    private String id;
    private String city;
    private String state;
    private String country;
    private int zipCode;
    private String streetAddress;

}
