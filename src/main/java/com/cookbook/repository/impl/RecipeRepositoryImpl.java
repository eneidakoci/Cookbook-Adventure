package com.cookbook.repository.impl;
import com.cookbook.domain.entity.CommentEntity;
import com.cookbook.domain.entity.RatingEntity;
import com.cookbook.domain.entity.RecipeEntity;
import com.cookbook.repository.RecipeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecipeRepositoryImpl implements RecipeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String SELECT_ALL_RECIPES = "SELECT r FROM RecipeEntity r";
    private static final String SELECT_RECIPE_BY_ID = "SELECT r FROM RecipeEntity r WHERE r.recipeId = :recipeId";
    private static final String SELECT_COMMENTS_BY_RECIPE_ID = "SELECT c FROM CommentEntity c WHERE c.recipeEntity.recipeId = :recipeId";
    private static final String SELECT_RATINGS_BY_RECIPE_ID = "SELECT r FROM RatingEntity r WHERE r.recipeEntity.recipeId = :recipeId";
    private static final String SELECT_POPULAR_RECIPES = "SELECT r FROM RecipeEntity r ORDER BY r.likes DESC";
    private static final String SELECT_NEWEST_RECIPES = "SELECT r FROM RecipeEntity r ORDER BY r.datePublished DESC";
    private static final String NUMBER_OF_RECIPES_PER_CATEGORY = "SELECT COUNT(r) FROM RecipeEntity r JOIN r.categories c WHERE c.categoryId = :categoryId";
    private static final String NUMBER_OF_RECIPES_BY_MEMBER = "SELECT COUNT(r) FROM RecipeEntity r WHERE r.memberEntity.memberId = :memberId";
    private static final Integer NUMBER_OF_RECIPES_SHOWN = 5;
    @Override
    public List<RecipeEntity> findAllRecipes() {
        return entityManager.createQuery(SELECT_ALL_RECIPES, RecipeEntity.class)
                .getResultList();
    }

    @Override
    public RecipeEntity findRecipeById(Integer id) {
        return entityManager.createQuery(SELECT_RECIPE_BY_ID, RecipeEntity.class)
                .setParameter("recipeId", id)
                .getSingleResult();
    }

    @Override
    public RecipeEntity createRecipe(RecipeEntity recipe) {
        entityManager.merge(recipe);
        return recipe;
    }

    @Override
    public RecipeEntity updateRecipe(Integer id, RecipeEntity recipe) {
        recipe.setRecipeId(id);
        return entityManager.merge(recipe);
    }

    @Override
    public void deleteRecipe(Integer id) {
        RecipeEntity recipe = entityManager.find(RecipeEntity.class, id);
        if(recipe != null){
            entityManager.remove(recipe);
        }
    }

    @Override
    public List<CommentEntity> findCommentsByRecipeId(Integer recipeId) {
        return entityManager.createQuery(SELECT_COMMENTS_BY_RECIPE_ID, CommentEntity.class)
                .setParameter("recipeId", recipeId)
                .getResultList();
    }

    @Override
    public List<RatingEntity> findRatingsByRecipeId(Integer recipeId) {
        return entityManager.createQuery(SELECT_RATINGS_BY_RECIPE_ID, RatingEntity.class)
                .setParameter("recipeId", recipeId)
                .getResultList();
    }

    @Override
    public List<RecipeEntity> findPopularRecipes() {
        return entityManager.createQuery(SELECT_POPULAR_RECIPES, RecipeEntity.class)
                .setMaxResults(NUMBER_OF_RECIPES_SHOWN) //only showing top 5 with the most likes.
                .getResultList();
    }

    @Override
    public List<RecipeEntity> findNewestRecipes() {
        return entityManager.createQuery(SELECT_NEWEST_RECIPES, RecipeEntity.class)
                .setMaxResults(NUMBER_OF_RECIPES_SHOWN)
                .getResultList();
    }

    @Override
    public Integer countRecipesByCategory(Integer categoryId) {
        return entityManager.createQuery(NUMBER_OF_RECIPES_PER_CATEGORY, Integer.class)
                .setParameter("categoryId", categoryId)
                .getSingleResult();
    }

    @Override
    public Integer countRecipesByMember(Integer memberId) {
        return entityManager.createQuery(NUMBER_OF_RECIPES_BY_MEMBER, Integer.class)
                .setParameter("memberId", memberId)
                .getSingleResult();
    }
}
