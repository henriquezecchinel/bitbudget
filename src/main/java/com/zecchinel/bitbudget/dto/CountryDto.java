package com.zecchinel.bitbudget.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CountryDto extends BaseEntityDto {
    public CountryDto(String name) {
        super(name);
    }
}