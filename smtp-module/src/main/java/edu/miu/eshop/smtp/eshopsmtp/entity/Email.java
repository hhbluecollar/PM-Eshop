package edu.miu.eshop.smtp.eshopsmtp.entity;

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
