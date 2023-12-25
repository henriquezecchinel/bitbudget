package com.zecchinel.bitbudget.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OwnerDto extends BaseEntityDto {
    public OwnerDto(Long id, String description) {
        super(id, description);
    }
}