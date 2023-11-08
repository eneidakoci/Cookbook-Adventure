package com.cookbook.service.impl;

import com.cookbook.domain.dto.CategoryDTO;
import com.cookbook.domain.dto.CategoryRequest;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.domain.entity.CategoryEntity;
import com.cookbook.domain.entity.RecipeEntity;
import com.cookbook.domain.mapper.CategoryMapper;
import com.cookbook.domain.mapper.RecipeMapper;
import com.cookbook.repository.CategoryRepository;
import com.cookbook.repository.RecipeRepository;
import com.cookbook.service.CategoryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RecipeRepository recipeRepository;
    @PersistenceContext
    private EntityManager entityManager;
    private CategoryMapper categoryMapper;//spranon autowired
    private RecipeMapper recipeMapper;

    @Override
    public List<CategoryDTO> findAllCategories() {
        List<CategoryEntity> categories = categoryRepository.findAllCategories();
        return categories.stream()
                .map(categoryMapper::categoryEntityToDto)
                .toList();
    }

    @Override
    public CategoryDTO findCategoryById(Integer id) {
        CategoryEntity category = categoryRepository.findCategoryById(id);
        if (category == null) {
            throw new RuntimeException("Category does not exist.");
        }
        return CategoryMapper.INSTANCE.categoryEntityToDto(categoryRepository.findCategoryById(id));
    }

    @Override
    public CategoryDTO createCategory(CategoryRequest categoryRequest) {
        CategoryEntity category = categoryMapper.categoryRequestToEntity(categoryRequest);
        category = categoryRepository.createCategory(category);
        return categoryMapper.categoryEntityToDto(category);
    }

    @Override
    public CategoryDTO updateCategory(Integer id, CategoryDTO categoryDTO) {
        Optional<CategoryEntity> categoryOptional = Optional.ofNullable(categoryRepository.findCategoryById(id));
        if (categoryOptional.isPresent()) {
            CategoryEntity existingCategory = categoryOptional.get();
            existingCategory.setName(categoryDTO.getName());
            CategoryEntity updatedCategory = categoryRepository.createCategory(existingCategory);
            return categoryMapper.categoryEntityToDto(updatedCategory);
        } else {
            return null;
        }
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.deleteCategory(id);
    }

    @Override
    public List<RecipeDTO> findRecipesByCategoryId(Integer categoryId) {
        String jpql = "SELECT r FROM RecipeEntity r WHERE r.category.id = :categoryId";
        TypedQuery<RecipeEntity> query = entityManager.createQuery(jpql, RecipeEntity.class);
        query.setParameter("categoryId", categoryId);
        List<RecipeEntity> recipeEntities = query.getResultList();

        return recipeEntities.stream()
                .map(recipeMapper::recipeEntityToDto)
                .toList();
    }


    @Override
    public List<RecipeDTO> findRecipesByCategoryName(String categoryName) {
        String jpql = "SELECT r FROM RecipeEntity r WHERE r.category.name = :categoryName";
        TypedQuery<RecipeEntity> query = entityManager.createQuery(jpql, RecipeEntity.class);
        query.setParameter("categoryName", categoryName);
        List<RecipeEntity> recipeEntities = query.getResultList();

        return recipeEntities.stream()
                .map(recipeMapper::recipeEntityToDto)
                .toList();
    }

}
