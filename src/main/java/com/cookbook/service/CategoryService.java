package com.cookbook.service;

import com.cookbook.domain.dto.CategoryDTO;
import com.cookbook.domain.dto.CategoryRequest;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.filter.Filter;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
public interface CategoryService {
    List<CategoryDTO> findAllCategories(Filter...filters);

    CategoryDTO findCategoryById(Integer id);

    CategoryDTO createCategory(CategoryRequest categoryRequest);

    CategoryDTO updateCategory(Integer id, CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(Integer id);

    List<RecipeDTO> findRecipesByCategoryId(Integer categoryId);

    List<RecipeDTO> findRecipesByCategoryName(String categoryName);
}