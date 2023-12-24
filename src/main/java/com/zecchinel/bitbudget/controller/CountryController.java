package com.zecchinel.bitbudget.controller;

import com.zecchinel.bitbudget.dto.CountryDto;
import com.zecchinel.bitbudget.model.Country;
import com.zecchinel.bitbudget.repository.BaseEntityRepository;
import com.zecchinel.bitbudget.repository.CountryRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/countries")
public class CountryController extends BaseEntityController<Country, CountryDto> {

    private final CountryRepository countryRepository;

    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    protected BaseEntityRepository<Country> getRepository() {
        return this.countryRepository;
    }

    @Override
    protected CountryDto convertToDto(Country entity) {
        return new CountryDto(entity.getId(), entity.getDescription());
    }
}