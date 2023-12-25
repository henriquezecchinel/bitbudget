package com.zecchinel.bitbudget.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SubCategoryDto extends BaseEntityDto {
    private MainCategoryDto mainCategory;

    public SubCategoryDto(Long id, String description, MainCategoryDto mainCategoryDto) {
        super(id, description);
        this.mainCategory = mainCategoryDto;
    }
}