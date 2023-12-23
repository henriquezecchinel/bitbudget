package com.zecchinel.bitbudget.controller;

import com.zecchinel.bitbudget.dto.TagDto;
import com.zecchinel.bitbudget.model.Tag;
import com.zecchinel.bitbudget.repository.BaseEntityRepository;
import com.zecchinel.bitbudget.repository.TagRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tags")
public class TagController extends BaseEntityController<Tag, TagDto> {

    private final TagRepository tagRepository;

    public TagController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    protected BaseEntityRepository<Tag> getRepository() {
        return this.tagRepository;
    }

    @Override
    protected TagDto convertToDto(Tag entity) {
        return new TagDto(entity.getName());
    }
}