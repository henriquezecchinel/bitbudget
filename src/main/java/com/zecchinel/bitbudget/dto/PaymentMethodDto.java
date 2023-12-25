package com.zecchinel.bitbudget.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PaymentMethodDto extends BaseEntityDto {
    public PaymentMethodDto(Long id, String description) {
        super(id, description);
    }
}