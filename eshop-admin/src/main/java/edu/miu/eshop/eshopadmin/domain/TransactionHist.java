package edu.miu.eshop.eshopadmin.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionHist {
    private String id;
    private Transaction transaction;
    private boolean response;
    private LocalDate date;
    private LocalTime time;
}
