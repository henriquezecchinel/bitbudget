package com.zecchinel.bitbudget.controller;

import com.zecchinel.bitbudget.dto.MainCategoryDto;
import com.zecchinel.bitbudget.model.MainCategory;
import com.zecchinel.bitbudget.repository.BaseEntityRepository;
import com.zecchinel.bitbudget.repository.MainCategoryRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main-categories")
public class MainCategoryController extends BaseEntityController<MainCategory, MainCategoryDto> {

    private final MainCategoryRepository mainCategoryRepository;

    public MainCategoryController(MainCategoryRepository mainCategoryRepository) {
        this.mainCategoryRepository = mainCategoryRepository;
    }

    @Override
    protected BaseEntityRepository<MainCategory> getRepository() {
        return this.mainCategoryRepository;
    }

    @Override
    protected MainCategoryDto convertToDto(MainCategory entity) {
        return new MainCategoryDto(entity.getId(), entity.getName());
    }
}