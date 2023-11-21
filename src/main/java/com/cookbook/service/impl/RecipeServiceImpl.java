package com.cookbook.service.impl;

import com.cookbook.domain.dto.CommentDTO;
import com.cookbook.domain.dto.RatingDTO;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.domain.dto.RecipeRequest;
import com.cookbook.domain.entity.CommentEntity;
import com.cookbook.domain.entity.RatingEntity;
import com.cookbook.domain.entity.RecipeEntity;
import com.cookbook.domain.exception.ResourceNotFoundException;
import com.cookbook.domain.mapper.CommentMapper;
import com.cookbook.domain.mapper.RatingMapper;
import com.cookbook.domain.mapper.RecipeMapper;
import com.cookbook.filter.Filter;
import com.cookbook.repository.CommentRepository;
import com.cookbook.repository.RatingRepository;
import com.cookbook.repository.RecipeRepository;
import com.cookbook.service.RecipeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public List<RecipeDTO> findAllRecipes(Filter...filters) {
        List<RecipeEntity> recipes = recipeRepository.findAllRecipes(filters);
        return recipes.stream()
                .map(RecipeMapper::recipeEntityToDto)
                .toList();
    }

    @Override
    public RecipeDTO findRecipeById(Integer id) throws ResourceNotFoundException {
        RecipeEntity recipeEntity = recipeRepository.findRecipeById(id);
        if(recipeEntity == null){
            throw new ResourceNotFoundException("Recipe does not exist.");
        }
        return RecipeMapper.recipeEntityToDto(recipeEntity);
    }

    @Override
    public RecipeDTO createRecipe(RecipeRequest recipeRequest) {
        RecipeEntity recipeEntity = RecipeMapper.requestDtoToEntity(recipeRequest);
        RecipeEntity savedRecipeEntity = recipeRepository.createRecipe(recipeEntity);
        return RecipeMapper.recipeEntityToDto(savedRecipeEntity);
    }

    @Override
    public RecipeDTO updateRecipe(Integer id, RecipeRequest recipeRequest) {
        RecipeEntity existingRecipeEntity = recipeRepository.findRecipeById(id);

        if (existingRecipeEntity != null) {
            RecipeEntity updatedRecipeEntity = RecipeMapper.requestDtoToEntity(recipeRequest);
            updatedRecipeEntity.setRecipeId(id);
            RecipeEntity savedRecipeEntity = recipeRepository.updateRecipe(id, updatedRecipeEntity);
            return RecipeMapper.recipeEntityToDto(savedRecipeEntity);
        }
       return null;
    }

    @Override
    public RecipeDTO deleteRecipe(Integer id) {
        try {
            RecipeEntity deletedRecipeEntity = recipeRepository.deleteRecipe(id);
            return RecipeMapper.recipeEntityToDto(deletedRecipeEntity);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Recipe not found with id: " + id);
        }
    }

    @Override
    public List<CommentDTO> findCommentsByRecipeId(Integer recipeId) {
        RecipeEntity recipeEntity = recipeRepository.findRecipeById(recipeId);
        if (recipeEntity != null) {
            List<CommentEntity> comments = commentRepository.findCommentsByRecipeId(recipeId);
            return comments.stream()
                    .map(CommentMapper::commentEntityToDto)
                    .toList();
        }
        return null;
    }

    @Override
    public List<RatingDTO> findRatingsByRecipeId(Integer recipeId) {
        List<RatingEntity> ratings = ratingRepository.findRatingsByRecipeId(recipeId);
        return ratings.stream()
                .map(RatingMapper::ratingEntityToDto)
                .toList();
    }

    @Override
    public List<RecipeDTO> findPopularRecipes() {
        List<RecipeEntity> popularRecipes = recipeRepository.findPopularRecipes();
        return popularRecipes.stream()
                .map(RecipeMapper::recipeEntityToDto)
                .toList();
    }

    @Override
    public List<RecipeDTO> findNewestRecipes() {
        List<RecipeEntity> newestRecipes = recipeRepository.findNewestRecipes();
        return newestRecipes.stream()
                .map(RecipeMapper::recipeEntityToDto)
                .toList();
    }

    @Override
    public Integer countRecipesByCategory(Integer categoryId) {
        return recipeRepository.countRecipesByCategory(categoryId);
    }

    @Override
    public Integer countRecipesByMember(Integer memberId) {
        return recipeRepository.countRecipesByMember(memberId);
    }
}
