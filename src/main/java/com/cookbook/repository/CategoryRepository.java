package com.cookbook.repository;

import com.cookbook.domain.entity.CategoryEntity;
import com.cookbook.domain.entity.RecipeEntity;

import java.util.List;


public interface CategoryRepository {
    List<CategoryEntity> findAllCategories();

    CategoryEntity findCategoryById(Integer id);

    CategoryEntity createCategory(CategoryEntity category);

    CategoryEntity updateCategory(Integer id, CategoryEntity category);

    CategoryEntity deleteCategory(Integer id);

    List<RecipeEntity> findRecipesByCategoryId(Integer categoryId);

    List<RecipeEntity> findRecipesByCategoryName(String categoryName);
}


