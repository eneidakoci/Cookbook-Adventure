package com.cookbook.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeCategoryDTO extends BaseDomainDTO{
        private RecipeDTO recipeDTO;
        private CategoryDTO categoryDTO;

    public RecipeCategoryDTO(LocalDateTime createdDate, LocalDateTime lastModified, boolean deleted, RecipeDTO recipeDTO, CategoryDTO categoryDTO) {
        super(createdDate, lastModified, deleted);
        this.recipeDTO = recipeDTO;
        this.categoryDTO = categoryDTO;
    }

    @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RecipeCategoryDTO that = (RecipeCategoryDTO) o;
            return Objects.equals(recipeDTO, that.recipeDTO) && Objects.equals(categoryDTO, that.categoryDTO);
        }
    
        @Override
        public int hashCode() {
            return Objects.hash(recipeDTO, categoryDTO);
        }
    }