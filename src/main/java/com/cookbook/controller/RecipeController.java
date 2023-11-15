package com.cookbook.controller;

import com.cookbook.aspect.MeasureTime;
import com.cookbook.domain.dto.CommentDTO;
import com.cookbook.domain.dto.RatingDTO;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.domain.dto.RecipeRequest;
import com.cookbook.domain.exception.GenericException;
import com.cookbook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;
    @MeasureTime
    @GetMapping
    public ResponseEntity<List<RecipeDTO>> findAllRecipes(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        List<RecipeDTO> recipes = recipeService.findAllRecipes(pageNumber, pageSize);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/{recipeId}")
    public ResponseEntity<RecipeDTO> findRecipeById(@PathVariable Integer recipeId) {
        RecipeDTO recipe = recipeService.findRecipeById(recipeId);
        if (recipe != null) {
            return ResponseEntity.ok(recipe);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

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

    @DeleteMapping("/{recipeId}")
    public ResponseEntity<RecipeDTO> deleteRecipe(@PathVariable Integer recipeId) {
        RecipeDTO deletedRecipe = recipeService.deleteRecipe(recipeId);
        if (deletedRecipe != null) {
            return ResponseEntity.ok(deletedRecipe);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{recipeId}/comments")
    public ResponseEntity<List<CommentDTO>> findCommentsByRecipeId(@PathVariable Integer recipeId) {
        List<CommentDTO> comments = recipeService.findCommentsByRecipeId(recipeId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{recipeId}/ratings")
    public ResponseEntity<List<RatingDTO>> findRatingsByRecipeId(@PathVariable Integer recipeId) {
        List<RatingDTO> ratings = recipeService.findRatingsByRecipeId(recipeId);
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/popular")
    public ResponseEntity<List<RecipeDTO>> findPopularRecipes() {
        List<RecipeDTO> popularRecipes = recipeService.findPopularRecipes();
        return ResponseEntity.ok(popularRecipes);
    }

    @GetMapping("/newest")
    public ResponseEntity<List<RecipeDTO>> findNewestRecipes() {
        List<RecipeDTO> newestRecipes = recipeService.findNewestRecipes();
        return ResponseEntity.ok(newestRecipes);
    }

//    @GetMapping("/countByCategory")
//    public ResponseEntity<Integer> countRecipesByCategory(@RequestParam Integer categoryId) {
//        if (categoryId == null || categoryId < 1) {
//            return ResponseEntity.badRequest().build();
//        }
//        Integer count = recipeService.countRecipesByCategory(categoryId);
//        return ResponseEntity.ok(count);
//    }
//
//    @GetMapping("/countByMember")
//    public ResponseEntity<Integer> countRecipesByMember(@RequestParam Integer memberId) {
//        if (memberId == null || memberId < 1) {
//            return ResponseEntity.badRequest().build();
//        }
//        Integer count = recipeService.countRecipesByMember(memberId);
//        return ResponseEntity.ok(count);
//    }

}