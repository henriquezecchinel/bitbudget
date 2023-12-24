package com.zecchinel.bitbudget.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CountryDto extends BaseEntityDto {
    public CountryDto(Long id, String name) {
        super(id, name);
    }
}