package com.cookbook.repository.impl;
import com.cookbook.domain.entity.CommentEntity;
import com.cookbook.domain.entity.MemberEntity;
import com.cookbook.domain.entity.RatingEntity;
import com.cookbook.domain.entity.RecipeEntity;
import com.cookbook.repository.RecipeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
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
    private static final String SELECT_NEWEST_RECIPES = "SELECT r FROM RecipeEntity r ORDER BY r.createdDate DESC";;
    private static final String NUMBER_OF_RECIPES_PER_CATEGORY = "SELECT COUNT(r) FROM RecipeEntity r JOIN r.categories c WHERE c.categoryId = :categoryId";
    private static final String NUMBER_OF_RECIPES_BY_MEMBER = "SELECT COUNT(r) FROM RecipeEntity r WHERE r.memberEntity.memberId = :memberId";
    private static final Integer NUMBER_OF_RECIPES_SHOWN = 5;
    private static final String SELECT_MEMBER_BY_RECIPE = "SELECT r FROM RecipeEntity r WHERE r.member = :member";
    @Override
    public List<RecipeEntity> findAllRecipes(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return entityManager.createQuery(SELECT_ALL_RECIPES, RecipeEntity.class)
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public RecipeEntity findRecipeById(Integer id) {
        return entityManager.createQuery(SELECT_RECIPE_BY_ID, RecipeEntity.class)
                .setParameter("recipeId", id)
                .getSingleResult();
    }
    @Transactional
    @Override
    public RecipeEntity createRecipe(RecipeEntity recipe) {
        entityManager.persist(recipe);
        recipe.setCreatedDate(LocalDateTime.now());
        recipe.setLastModified(LocalDateTime.now());
        return recipe;
    }
    @Transactional
    @Override
    public RecipeEntity updateRecipe(Integer id, RecipeEntity recipe) {
        recipe.setRecipeId(id);
        recipe.setLastModified(LocalDateTime.now());
        return entityManager.merge(recipe);
    }
    @Transactional
    @Override
    public RecipeEntity deleteRecipe(Integer id) {
        RecipeEntity recipe = entityManager.find(RecipeEntity.class, id);
        if(recipe != null){
            entityManager.remove(recipe);
            return recipe;
        }else {
            throw new EntityNotFoundException("Recipe with ID " + id + " not found");
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

    @Override
    public List<RecipeEntity> findRecipesByMember(MemberEntity memberEntity) {
        return entityManager.createQuery(SELECT_MEMBER_BY_RECIPE, RecipeEntity.class)
                .setParameter("member", memberEntity)
                .getResultList();
    }
}
