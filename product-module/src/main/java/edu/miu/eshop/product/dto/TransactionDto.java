package edu.miu.eshop.product.dto;

import edu.miu.eshop.product.entity.Card;
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
