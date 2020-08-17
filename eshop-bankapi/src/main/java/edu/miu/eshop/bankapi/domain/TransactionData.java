package edu.miu.eshop.bankapi.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionData {
    private double total;
    private double success;
    private double fail;
    private double balance;

    public TransactionData(double total, double success, double fail) {
        this.total = total;
        this.success = success;
        this.fail = fail;
    }
}
