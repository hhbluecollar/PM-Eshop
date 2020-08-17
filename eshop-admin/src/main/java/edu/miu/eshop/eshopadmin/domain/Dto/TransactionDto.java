package edu.miu.eshop.eshopadmin.domain.Dto;

import edu.miu.eshop.eshopadmin.domain.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private Card card;
    private double cost;
}
