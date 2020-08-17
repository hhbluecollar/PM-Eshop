package edu.miu.eshop.eshopadmin.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentMethod {
    private String id;
    private String url;
    private String name;
    private String description;
    private int rangeFrom;
    private int rangeTo;
}
