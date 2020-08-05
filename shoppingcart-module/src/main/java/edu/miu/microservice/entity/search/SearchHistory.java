package edu.miu.microservice.entity.search;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@ToString
@Setter
@Getter
@AllArgsConstructor
@Document
public class SearchHistory {

    @Id
    private String id;
    private String customerId;
    private String searchWord;
    private String customerIP;
    private LocalDateTime searchDate;

}
