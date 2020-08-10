package edu.miu.eshop.product.api.client;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@ToString
public class Email {

    private String [] receivers;
    private String subject;
    private  String body;
    private  String [] attachmentsPath;
}
