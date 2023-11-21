
package com.cookbook.controller;

import com.cookbook.aspect.MeasureTime;
import com.cookbook.configuration.AdminAccess;
import com.cookbook.configuration.UserAndAdminAccess;
import com.cookbook.domain.dto.CategoryDTO;
import com.cookbook.domain.dto.CategoryRequest;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.domain.exception.GenericException;
import com.cookbook.domain.exception.ResourceNotFoundException;
import com.cookbook.filter.Filter;
import com.cookbook.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @UserAndAdminAccess
    @MeasureTime
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAllCategories(@RequestParam(required = false) Integer pageNumber,
                                                               @RequestParam(required = false) Integer pageSize,
                                                               @RequestParam(required = false) String sort,
                                                               @RequestParam(required = false) String name) {
        Filter nameFilter = new Filter("name",name,"LIKE", null, pageNumber, pageSize);
        if(sort != null){
            String[] sortValue = sort.split(":");
            if(Objects.equals(sortValue[0], "name")){
                nameFilter.setSort(sortValue[1]);
            }else{
                throw new RuntimeException("Invalid sort field.");
            }
        }
        List<CategoryDTO> categories = categoryService.findAllCategories(nameFilter);
        if(categories == null){
            throw new ResourceNotFoundException("Categories not found.");
        }
        return ResponseEntity.ok(categories);
    }

    @UserAndAdminAccess
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> findCategoryById(@PathVariable Integer categoryId) {
        CategoryDTO category = categoryService.findCategoryById(categoryId);
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @AdminAccess
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryRequest categoryRequest) {
        if(categoryRequest.getName() == null || categoryRequest.getName().isEmpty()){
            throw new GenericException("Category name is required.");
        }
        CategoryDTO createdCategory = categoryService.createCategory(categoryRequest);
        if (createdCategory != null) {
            URI locationOfCreatedCategory = URI.create("/api/categories/" + createdCategory.getCategoryId());
            return ResponseEntity.created(locationOfCreatedCategory).body(createdCategory);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @AdminAccess
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Integer categoryId,
                                                      @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO updatedCategory = categoryService.updateCategory(categoryId, categoryDTO);
        if (updatedCategory != null) {
            return ResponseEntity.ok(updatedCategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @AdminAccess
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Integer categoryId) {
        CategoryDTO deletedCategory = categoryService.deleteCategory(categoryId);
        if (deletedCategory != null) {
            return ResponseEntity.ok(deletedCategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @UserAndAdminAccess
    @GetMapping("/{categoryId}/recipes")
    public ResponseEntity<List<RecipeDTO>> findRecipesByCategoryId(@PathVariable Integer categoryId) {
        List<RecipeDTO> recipes = categoryService.findRecipesByCategoryId(categoryId);
        return ResponseEntity.ok(recipes);
    }

}