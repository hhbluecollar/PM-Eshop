package edu.miu.eshop.bankapi.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BooleanDto {
    private  boolean bool;
    public static BooleanDto build(boolean bool){
        return new BooleanDto(bool);
    }
}