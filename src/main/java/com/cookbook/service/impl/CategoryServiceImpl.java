package com.cookbook.service.impl;

import com.cookbook.domain.dto.CategoryDTO;
import com.cookbook.domain.dto.CategoryRequest;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.domain.entity.CategoryEntity;
import com.cookbook.domain.entity.RecipeEntity;
import com.cookbook.domain.exception.ResourceNotFoundException;
import com.cookbook.domain.mapper.impl.CategoryMapperImpl;
import com.cookbook.domain.mapper.impl.RecipeMapperImpl;
import com.cookbook.repository.CategoryRepository;
import com.cookbook.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> findAllCategories(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        List<CategoryEntity> categories = categoryRepository.findAllCategories(pageNumber, pageSize);
        return categories.stream()
                .map(CategoryMapperImpl::categoryEntityToDto)
                .toList();
    }

    @Override
    public CategoryDTO findCategoryById(Integer id) throws ResourceNotFoundException {
        CategoryEntity category = categoryRepository.findCategoryById(id);
        if (category == null) {
            throw new ResourceNotFoundException("Category does not exist.");
        }
        return CategoryMapperImpl.categoryEntityToDto(categoryRepository.findCategoryById(id));
    }

    @Override
    public CategoryDTO createCategory(CategoryRequest categoryRequest) {
        CategoryEntity category = CategoryMapperImpl.categoryRequestToEntity(categoryRequest);
        category = categoryRepository.createCategory(category);
        return CategoryMapperImpl.categoryEntityToDto(category);
    }

    @Override
    public CategoryDTO updateCategory(Integer id, CategoryDTO categoryDTO) {
        Optional<CategoryEntity> categoryOptional = Optional.ofNullable(categoryRepository.findCategoryById(id));
        if (categoryOptional.isPresent()) {
            CategoryEntity existingCategory = categoryOptional.get();
            existingCategory.setName(categoryDTO.getName());
            CategoryEntity updatedCategory = categoryRepository.createCategory(existingCategory);
            return CategoryMapperImpl.categoryEntityToDto(updatedCategory);
        } else {
            return null;
        }
    }

    @Override
    public CategoryDTO deleteCategory(Integer id) {
        try {
            CategoryEntity deletedCategory = categoryRepository.deleteCategory(id);
            return CategoryMapperImpl.categoryEntityToDto(deletedCategory);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Category not found with id: " + id);
        }
    }

    @Override
    public List<RecipeDTO> findRecipesByCategoryId(Integer categoryId) {
        List<RecipeEntity> recipeEntities = categoryRepository.findRecipesByCategoryId(categoryId);

        return recipeEntities.stream()
                .map(RecipeMapperImpl::recipeEntityToDto)
                .toList();
    }


    @Override
    public List<RecipeDTO> findRecipesByCategoryName(String categoryName) {
        List<RecipeEntity> recipeEntities = categoryRepository.findRecipesByCategoryName(categoryName);

        return recipeEntities.stream()
                .map(RecipeMapperImpl::recipeEntityToDto)
                .toList();
    }

}
