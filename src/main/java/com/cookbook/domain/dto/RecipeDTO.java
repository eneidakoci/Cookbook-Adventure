package com.cookbook.domain.dto;

import com.cookbook.domain.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO extends BaseDomainDTO{
    private Integer recipeId;
    private Set<CategoryEntity> categories;
    private String recipeName;
    private String ingredients;
    private String description;
    private Integer likes;

    public RecipeDTO(LocalDateTime createdDate, LocalDateTime lastModified, boolean deleted, Integer recipeId, Set<CategoryEntity> categories, String recipeName, String ingredients, String description, Integer likes) {
        super(createdDate, lastModified, deleted);
        this.recipeId = recipeId;
        this.categories = categories;
        this.recipeName = recipeName;
        this.ingredients = ingredients;
        this.description = description;
        this.likes = likes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeDTO recipeDTO = (RecipeDTO) o;
        return Objects.equals(recipeId, recipeDTO.recipeId) && Objects.equals(categories, recipeDTO.categories) && Objects.equals(recipeName, recipeDTO.recipeName) && Objects.equals(ingredients, recipeDTO.ingredients) && Objects.equals(description, recipeDTO.description) && Objects.equals(likes, recipeDTO.likes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, categories, recipeName, ingredients, description, likes);
    }
}
