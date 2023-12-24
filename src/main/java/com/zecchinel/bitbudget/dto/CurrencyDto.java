package com.zecchinel.bitbudget.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CurrencyDto extends BaseEntityDto {
    public CurrencyDto(Long id, String name) {
        super(id, name);
    }
}