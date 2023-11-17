package com.cookbook.controller;

import com.cookbook.aspect.MeasureTime;
import com.cookbook.configuration.AdminAccess;
import com.cookbook.configuration.UserAndAdminAccess;
import com.cookbook.domain.dto.CommentDTO;
import com.cookbook.domain.dto.RatingDTO;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.domain.dto.RecipeRequest;
import com.cookbook.domain.exception.GenericException;
import com.cookbook.domain.exception.ResourceNotFoundException;
import com.cookbook.filter.Filter;
import com.cookbook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @UserAndAdminAccess
    @MeasureTime
    @GetMapping
    public ResponseEntity<List<RecipeDTO>> findAllRecipes(@RequestParam Integer pageNumber,
                                                          @RequestParam Integer pageSize,
                                                          @RequestParam(required = false) String sort,
                                                          @RequestParam(required = false) String likes) {
        Filter likesFilter = new Filter("likes",likes,"LIKE", null, pageNumber, pageSize);
        if(sort != null){
            String[] sortValue = sort.split(":");
            if(Objects.equals(sortValue[0], "likes")){
                likesFilter.setSort(sortValue[1]);
            }else{
                throw new RuntimeException("Invalid sort field.");
            }
        }
        List<RecipeDTO> recipeDTOS = recipeService.findAllRecipes(likesFilter);
        if(recipeDTOS == null){
            throw new ResourceNotFoundException("recipes not found.");
        }
        return ResponseEntity.ok(recipeDTOS);
    }

    @UserAndAdminAccess
    @GetMapping("/{recipeId}")
    public ResponseEntity<RecipeDTO> findRecipeById(@PathVariable Integer recipeId) {
        RecipeDTO recipe = recipeService.findRecipeById(recipeId);
        if (recipe != null) {
            return ResponseEntity.ok(recipe);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @UserAndAdminAccess
    @PostMapping
    public ResponseEntity<RecipeDTO> createRecipe(@RequestBody RecipeRequest recipeRequest) {
        if(recipeRequest.getRecipeName() == null || recipeRequest.getRecipeName().isEmpty()){
            throw new GenericException("Recipe name is required.");
        }
        RecipeDTO createdRecipe = recipeService.createRecipe(recipeRequest);
        if (createdRecipe != null) {
            URI locationOfCreatedRecipe = URI.create("/api/recipes/" + createdRecipe.getRecipeId());
            return ResponseEntity.created(locationOfCreatedRecipe).body(createdRecipe);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @UserAndAdminAccess
    @PutMapping("/{recipeId}")
    public ResponseEntity<RecipeDTO> updateRecipe(
            @PathVariable Integer recipeId,
            @RequestBody RecipeRequest recipeRequest) {
        RecipeDTO updatedRecipe = recipeService.updateRecipe(recipeId, recipeRequest);
        if (updatedRecipe != null) {
            return ResponseEntity.ok(updatedRecipe);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @AdminAccess
    @DeleteMapping("/{recipeId}")
    public ResponseEntity<RecipeDTO> deleteRecipe(@PathVariable Integer recipeId) {
        RecipeDTO deletedRecipe = recipeService.deleteRecipe(recipeId);
        if (deletedRecipe != null) {
            return ResponseEntity.ok(deletedRecipe);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @UserAndAdminAccess
    @GetMapping("/{recipeId}/comments")
    public ResponseEntity<List<CommentDTO>> findCommentsByRecipeId(@PathVariable Integer recipeId) {
        List<CommentDTO> comments = recipeService.findCommentsByRecipeId(recipeId);
        return ResponseEntity.ok(comments);
    }

    @UserAndAdminAccess
    @GetMapping("/{recipeId}/ratings")
    public ResponseEntity<List<RatingDTO>> findRatingsByRecipeId(@PathVariable Integer recipeId) {
        List<RatingDTO> ratings = recipeService.findRatingsByRecipeId(recipeId);
        return ResponseEntity.ok(ratings);
    }

    @UserAndAdminAccess
    @GetMapping("/popular")
    public ResponseEntity<List<RecipeDTO>> findPopularRecipes() {
        List<RecipeDTO> popularRecipes = recipeService.findPopularRecipes();
        return ResponseEntity.ok(popularRecipes);
    }

    @UserAndAdminAccess
    @GetMapping("/newest")
    public ResponseEntity<List<RecipeDTO>> findNewestRecipes() {
        List<RecipeDTO> newestRecipes = recipeService.findNewestRecipes();
        return ResponseEntity.ok(newestRecipes);
    }
}