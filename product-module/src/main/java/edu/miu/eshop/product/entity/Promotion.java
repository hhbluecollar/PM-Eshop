package edu.miu.eshop.product.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Promotion {

    @Id
    private  String id;
    private  String productId;
    private  String promoName;
    private String PromotionDescription;
    private  long promotionPercentage;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}
