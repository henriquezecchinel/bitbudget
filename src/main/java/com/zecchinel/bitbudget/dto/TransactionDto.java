package com.zecchinel.bitbudget.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class TransactionDto extends BaseEntityDto {

    private Date purchaseDate;

    private Date usageDate;

    private Double amount;

    private CurrencyDto currency;

    private int installments;

    private int monthsDuration;

    private SubCategoryDto subCategory;

    private OwnerDto owner;

    private CountryDto country;

    private TagDto tag;

    private PaymentMethodDto paymentMethod;
}