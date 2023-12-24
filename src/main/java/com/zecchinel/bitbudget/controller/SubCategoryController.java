package com.zecchinel.bitbudget.controller;

import com.zecchinel.bitbudget.dto.MainCategoryDto;
import com.zecchinel.bitbudget.dto.SubCategoryDto;
import com.zecchinel.bitbudget.model.MainCategory;
import com.zecchinel.bitbudget.model.SubCategory;
import com.zecchinel.bitbudget.repository.BaseEntityRepository;
import com.zecchinel.bitbudget.repository.MainCategoryRepository;
import com.zecchinel.bitbudget.repository.SubCategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/sub-categories")
public class SubCategoryController extends BaseEntityController<SubCategory, SubCategoryDto> {

    private final SubCategoryRepository subCategoryRepository;
    private final MainCategoryRepository mainCategoryRepository;

    public SubCategoryController(SubCategoryRepository subCategoryRepository, MainCategoryRepository mainCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
        this.mainCategoryRepository = mainCategoryRepository;
    }

    @Override
    protected BaseEntityRepository<SubCategory> getRepository() {
        return this.subCategoryRepository;
    }

    @Override
    protected SubCategoryDto convertToDto(SubCategory entity) {
        MainCategoryDto mainCategoryDto = new MainCategoryDto(entity.getMainCategory().getId(), entity.getMainCategory().getDescription());
        return new SubCategoryDto(entity.getId(), entity.getDescription(), mainCategoryDto);
    }

    @Override
    public ResponseEntity<?> create(@RequestBody SubCategory entity) {
        try {
            MainCategory mainCategory = entity.getMainCategory();
            if (mainCategory != null) {
                Optional<MainCategory> mainCategoryOpt = mainCategoryRepository.findById(mainCategory.getId());
                if (mainCategoryOpt.isPresent()) {
                    SubCategory subCategory = new SubCategory();
                    subCategory.setMainCategory(mainCategoryOpt.get());
                    subCategory.setDescription(entity.getDescription());

                    SubCategory savedSubCategory = subCategoryRepository.save(subCategory);
                    return ResponseEntity.ok(convertToDto(savedSubCategory));
                } else {
                    return ResponseEntity.badRequest().body("No MainCategory with the provided ID exists");
                }
            } else {
                return ResponseEntity.badRequest().body("MainCategory has not been provided");
            }
        } catch (Exception e) {
            // TODO: Improve error handling
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}