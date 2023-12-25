package com.zecchinel.bitbudget.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MainCategoryDto extends BaseEntityDto {
    public MainCategoryDto(Long id, String description) {
        super(id, description);
    }
}