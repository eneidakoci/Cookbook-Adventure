package com.cookbook.repository;

import com.cookbook.domain.entity.CategoryEntity;
import com.cookbook.domain.entity.RecipeEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface CategoryRepository {
    List<CategoryEntity> findAllCategories(Integer pageNumber, Integer pageSize);

    CategoryEntity findCategoryById(Integer id);

    CategoryEntity createCategory(CategoryEntity category);

    CategoryEntity updateCategory(Integer id, CategoryEntity category);

    CategoryEntity deleteCategory(Integer id);

    List<RecipeEntity> findRecipesByCategoryId(Integer categoryId);

    List<RecipeEntity> findRecipesByCategoryName(String categoryName);
}


