package com.cookbook.service.impl;

import com.cookbook.domain.dto.CommentDTO;
import com.cookbook.domain.dto.RatingDTO;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.domain.dto.RecipeRequest;
import com.cookbook.domain.entity.CommentEntity;
import com.cookbook.domain.entity.MemberEntity;
import com.cookbook.domain.entity.RatingEntity;
import com.cookbook.domain.entity.RecipeEntity;
import com.cookbook.domain.mapper.impl.CommentMapperImpl;
import com.cookbook.domain.mapper.impl.MemberMapperImpl;
import com.cookbook.domain.mapper.impl.RatingMapperImpl;
import com.cookbook.domain.mapper.impl.RecipeMapperImpl;
import com.cookbook.repository.CommentRepository;
import com.cookbook.repository.RatingRepository;
import com.cookbook.repository.RecipeRepository;
import com.cookbook.service.RecipeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
    public List<RecipeDTO> findAllRecipes(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        List<RecipeEntity> recipes = recipeRepository.findAllRecipes(pageNumber, pageSize);
        return recipes.stream()
                .map(RecipeMapperImpl::recipeEntityToDto)
                .toList();
    }

    @Override
    public RecipeDTO findRecipeById(Integer id) {
        RecipeEntity recipeEntity = recipeRepository.findRecipeById(id);
        return RecipeMapperImpl.recipeEntityToDto(recipeEntity);
    }

    @Override
    public RecipeDTO createRecipe(RecipeRequest recipeRequest) {
        RecipeEntity recipeEntity = RecipeMapperImpl.requestDtoToEntity(recipeRequest);
        RecipeEntity savedRecipeEntity = recipeRepository.createRecipe(recipeEntity);
        return RecipeMapperImpl.recipeEntityToDto(savedRecipeEntity);
    }

    @Override
    public RecipeDTO updateRecipe(Integer id, RecipeRequest recipeRequest) {
        RecipeEntity existingRecipeEntity = recipeRepository.findRecipeById(id);

        if (existingRecipeEntity != null) {
            RecipeEntity updatedRecipeEntity = RecipeMapperImpl.requestDtoToEntity(recipeRequest);
            updatedRecipeEntity.setRecipeId(id);
            RecipeEntity savedRecipeEntity = recipeRepository.updateRecipe(id, updatedRecipeEntity);
            return RecipeMapperImpl.recipeEntityToDto(savedRecipeEntity);
        }
       return null;
    }

    @Override
    public RecipeDTO deleteRecipe(Integer id) {
        try {
            RecipeEntity deletedRecipeEntity = recipeRepository.deleteRecipe(id);
            return RecipeMapperImpl.recipeEntityToDto(deletedRecipeEntity);
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
                    .map(CommentMapperImpl::commentEntityToDto)
                    .toList();
        }
        return null;
    }

    @Override
    public List<RatingDTO> findRatingsByRecipeId(Integer recipeId) {
        List<RatingEntity> ratings = ratingRepository.findRatingsByRecipeId(recipeId);
        return ratings.stream()
                .map(RatingMapperImpl::ratingEntityToDto)
                .toList();
    }

    @Override
    public List<RecipeDTO> findPopularRecipes() {
        List<RecipeEntity> popularRecipes = recipeRepository.findPopularRecipes();
        return popularRecipes.stream()
                .map(RecipeMapperImpl::recipeEntityToDto)
                .toList();
    }

    @Override
    public List<RecipeDTO> findNewestRecipes() {
        List<RecipeEntity> newestRecipes = recipeRepository.findNewestRecipes();
        return newestRecipes.stream()
                .map(RecipeMapperImpl::recipeEntityToDto)
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
