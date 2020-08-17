package edu.miu.eshop.eshopadmin.domain;

// HH

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.Id;
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
    private String vendorId;
    private  String promoName;
    private String PromotionDescription;
    private  long promotionPercentage;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}
