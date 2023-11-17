package com.cookbook.repository;

import com.cookbook.domain.entity.CategoryEntity;
import com.cookbook.domain.entity.RecipeEntity;
import com.cookbook.filter.Filter;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface CategoryRepository {
    List<CategoryEntity> findAllCategories(Filter...filters);

    CategoryEntity findCategoryById(Integer id);

    CategoryEntity createCategory(CategoryEntity category);

    CategoryEntity updateCategory(Integer id, CategoryEntity category);

    CategoryEntity deleteCategory(Integer id);

    List<RecipeEntity> findRecipesByCategoryId(Integer categoryId);

    List<RecipeEntity> findRecipesByCategoryName(String categoryName);
}


