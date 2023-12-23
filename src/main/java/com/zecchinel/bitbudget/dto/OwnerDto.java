package com.zecchinel.bitbudget.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OwnerDto extends BaseEntityDto {
    public OwnerDto(String name) {
        super(name);
    }
}