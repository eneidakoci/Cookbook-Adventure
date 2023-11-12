package com.cookbook.controller;

import com.cookbook.domain.dto.CategoryDTO;
import com.cookbook.domain.dto.CategoryRequest;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAllCategories(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        List<CategoryDTO> categories = categoryService.findAllCategories(pageNumber, pageSize);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> findCategoryById(@PathVariable Integer categoryId) {
        CategoryDTO category = categoryService.findCategoryById(categoryId);
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryRequest categoryRequest) {
        CategoryDTO createdCategory = categoryService.createCategory(categoryRequest);
        if (createdCategory != null) {
            URI locationOfCreatedCategory = URI.create("/api/categories/" + createdCategory.getCategoryId());
            return ResponseEntity.created(locationOfCreatedCategory).body(createdCategory);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(
            @PathVariable Integer categoryId,
            @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO updatedCategory = categoryService.updateCategory(categoryId, categoryDTO);
        if (updatedCategory != null) {
            return ResponseEntity.ok(updatedCategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Integer categoryId) {
        CategoryDTO deletedCategory = categoryService.deleteCategory(categoryId);
        if (deletedCategory != null) {
            return ResponseEntity.ok(deletedCategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{categoryId}/recipes")
    public ResponseEntity<List<RecipeDTO>> findRecipesByCategoryId(@PathVariable Integer categoryId) {
        List<RecipeDTO> recipes = categoryService.findRecipesByCategoryId(categoryId);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/recipes")
    public ResponseEntity<List<RecipeDTO>> findRecipesByCategoryName(@RequestParam String categoryName) {
        List<RecipeDTO> recipes = categoryService.findRecipesByCategoryName(categoryName);
        return ResponseEntity.ok(recipes);
    }
    @GetMapping("/error")
    public ResponseEntity<String> handleError() {
        return ResponseEntity.ok().body("Error happened");
    }

}
