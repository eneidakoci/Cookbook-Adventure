package com.cookbook.service;

import com.cookbook.domain.dto.CategoryDTO;
import com.cookbook.domain.dto.CategoryRequest;
import com.cookbook.domain.dto.RecipeDTO;

import java.util.List;
public interface CategoryService {
    List<CategoryDTO> findAllCategories();

    CategoryDTO findCategoryById(Integer id);

    CategoryDTO createCategory(CategoryRequest categoryRequest);

    CategoryDTO updateCategory(Integer id, CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(Integer id);

    List<RecipeDTO> findRecipesByCategoryId(Integer categoryId);

    List<RecipeDTO> findRecipesByCategoryName(String categoryName);
}