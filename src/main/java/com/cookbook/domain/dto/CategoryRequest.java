package com.cookbook.domain.dto;

import com.cookbook.domain.entity.RecipeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest extends BaseDomainRequest{
    private String name;
    private List<RecipeEntity> recipeEntities = new ArrayList<>();

    public CategoryRequest(String name) {
        this.name = name;
    }
}
