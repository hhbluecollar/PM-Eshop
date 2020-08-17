package edu.miu.eshop.eshopadmin.domain.Dto;

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