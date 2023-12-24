package com.zecchinel.bitbudget.controller;

import com.zecchinel.bitbudget.dto.CurrencyDto;
import com.zecchinel.bitbudget.model.Currency;
import com.zecchinel.bitbudget.repository.BaseEntityRepository;
import com.zecchinel.bitbudget.repository.CurrencyRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currencies")
public class CurrencyController extends BaseEntityController<Currency, CurrencyDto> {

    private final CurrencyRepository currencyRepository;

    public CurrencyController(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    protected BaseEntityRepository<Currency> getRepository() {
        return this.currencyRepository;
    }

    @Override
    protected CurrencyDto convertToDto(Currency entity) {
        return new CurrencyDto(entity.getId(), entity.getDescription());
    }
}