package edu.miu.eshop.eshopuser.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerScoreDto {
    private String customerId;
    private String firstName;
    private String lastName;
    private double totalScore;
    public static CustomerScoreDto build(Customer customer){
        return new CustomerScoreDto(
                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getTotalScore());
    }
}
