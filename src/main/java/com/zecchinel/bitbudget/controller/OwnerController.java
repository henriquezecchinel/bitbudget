package com.zecchinel.bitbudget.controller;

import com.zecchinel.bitbudget.dto.OwnerDto;
import com.zecchinel.bitbudget.model.Owner;
import com.zecchinel.bitbudget.repository.BaseEntityRepository;
import com.zecchinel.bitbudget.repository.OwnerRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owners")
public class OwnerController extends BaseEntityController<Owner, OwnerDto> {

    private final OwnerRepository ownerRepository;

    public OwnerController(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    protected BaseEntityRepository<Owner> getRepository() {
        return this.ownerRepository;
    }

    @Override
    protected OwnerDto convertToDto(Owner entity) {
        return new OwnerDto(entity.getId(), entity.getDescription());
    }
}