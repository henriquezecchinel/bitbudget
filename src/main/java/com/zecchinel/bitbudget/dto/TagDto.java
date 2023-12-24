package com.zecchinel.bitbudget.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TagDto extends BaseEntityDto {
    public TagDto(Long id, String name) {
        super(id, name);
    }
}