package com.zecchinel.bitbudget.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SubCategoryDto extends BaseEntityDto {
    private MainCategoryDto mainCategory;

    public SubCategoryDto(Long id, String name, MainCategoryDto mainCategoryDto) {
        super(id, name);
        this.mainCategory = mainCategoryDto;
    }
}