package edu.miu.eshop.product.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@ToString
@Setter
@Getter
@Document
public class SearchHistory {

    @Id
    private String id;
    private String userName;
    private String searchWord;
    private String customerIP;
    private LocalDateTime searchDate;

}
