package com.cookbook.domain.dto;

import com.cookbook.domain.entity.RecipeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO extends BaseDomainDTO{
    private Integer categoryId;
    private List<RecipeEntity> recipeEntities;
    private String name;
    public CategoryDTO(LocalDateTime createdDate, LocalDateTime lastModified, boolean deleted, Integer categoryId, List<RecipeEntity> recipeEntities, String name) {
        super(createdDate, lastModified, deleted);
        this.categoryId = categoryId;
        this.recipeEntities = recipeEntities;
        this.name = name;
    }

    public CategoryDTO(Integer categoryId,String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDTO that = (CategoryDTO) o;
        return Objects.equals(categoryId, that.categoryId) && Objects.equals(recipeEntities, that.recipeEntities) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, recipeEntities, name);
    }
}
