package com.zecchinel.bitbudget.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class SubCategory extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "main_category_id", nullable = false)
    private MainCategory mainCategory;

    // Since we can have multiple subcategories with the same name for different main categories:
    // TODO: update the unique key to be (`name` + `main_category_id`) instead of (`name`)
}