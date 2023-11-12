package com.cookbook.repository.impl;

import com.cookbook.domain.entity.CategoryEntity;
import com.cookbook.domain.entity.RecipeEntity;
import com.cookbook.repository.CategoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    @PersistenceContext
    private EntityManager entityManager;

    //Queries that will be used later on
    private static final String SELECT_ALL_CATEGORIES = "SELECT c FROM CategoryEntity c";
    private static final String SELECT_CATEGORY_BY_ID = "SELECT c FROM CategoryEntity c WHERE c.categoryId = :categoryId";
    private static final String SELECT_RECIPES_BY_CATEGORY_ID = "SELECT r FROM RecipeEntity r JOIN r.categories c WHERE c.categoryId = :categoryId";
    private static final String SELECT_RECIPES_BY_CATEGORY_NAME = "SELECT r FROM RecipeEntity r WHERE :categoryName MEMBER OF r.categories";

    @Override
    public List<CategoryEntity> findAllCategories(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return entityManager.createQuery(SELECT_ALL_CATEGORIES, CategoryEntity.class)
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public CategoryEntity findCategoryById(Integer id) {
        return entityManager.createQuery(SELECT_CATEGORY_BY_ID, CategoryEntity.class)
                .setParameter("categoryId", id)
                .getSingleResult();
    }

    @Transactional
    @Override
    public CategoryEntity createCategory(CategoryEntity category) {
        entityManager.persist(category);
        return category;
    }

    @Transactional
    @Override
    public CategoryEntity updateCategory(Integer id, CategoryEntity category) {
        category.setCategoryId(id);  // Set the ID of the category to be updated
        category.setLastModified(LocalDateTime.now());
        return entityManager.merge(category);
    }

    @Transactional
    @Override
    public CategoryEntity deleteCategory(Integer id) {
        CategoryEntity category = entityManager.find(CategoryEntity.class, id);
        if (category != null) {
            entityManager.remove(category);
            category.setDeleted(true);
            return category;
        } else {
            throw new EntityNotFoundException("Category with ID " + id + " not found");
        }
    }

    @Override
    public List<RecipeEntity> findRecipesByCategoryId(Integer categoryId) {
        return entityManager.createQuery(SELECT_RECIPES_BY_CATEGORY_ID, RecipeEntity.class)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

    @Override
    public List<RecipeEntity> findRecipesByCategoryName(String categoryName) {
        return entityManager.createQuery(SELECT_RECIPES_BY_CATEGORY_NAME, RecipeEntity.class)
                .setParameter("categoryName", categoryName)
                .getResultList();
    }

}