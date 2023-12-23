package com.zecchinel.bitbudget.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TagDto extends BaseEntityDto {
    public TagDto(String name) {
        super(name);
    }
}