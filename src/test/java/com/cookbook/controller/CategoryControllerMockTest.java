package com.cookbook.controller;
import com.cookbook.domain.dto.CategoryDTO;
import com.cookbook.domain.dto.CategoryRequest;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.domain.exception.GenericException;
import com.cookbook.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CategoryControllerMockTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllCategories() {
        // Mocking the service method
        when(categoryService.findAllCategories(any())).thenReturn(Arrays.asList(new CategoryDTO(), new CategoryDTO()));
        // Testing the controller method
        ResponseEntity<List<CategoryDTO>> responseEntity = categoryController.findAllCategories(null, null, null, null);
        // Assertions
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(2, responseEntity.getBody().size());
    }

    @Test
    void testFindCategoryById() {
        when(categoryService.findCategoryById(anyInt())).thenReturn(new CategoryDTO());

        ResponseEntity<CategoryDTO> responseEntity = categoryController.findCategoryById(1);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(CategoryDTO.class, responseEntity.getBody().getClass());
    }

    @Test
    void testCreateCategoryWithValidRequest() {
        CategoryDTO expectedCategory = new CategoryDTO();
        expectedCategory.setName("Test Category");
        when(categoryService.createCategory(any(CategoryRequest.class))).thenReturn(expectedCategory);

        CategoryRequest validRequest = new CategoryRequest();
        validRequest.setName("Test Category");
        ResponseEntity<CategoryDTO> responseEntity = categoryController.createCategory(validRequest);

        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(CategoryDTO.class, responseEntity.getBody().getClass());
        assertEquals("Test Category", responseEntity.getBody().getName());
    }
    @Test
    void testCreateCategoryWithoutName() {
        when(categoryService.createCategory(any(CategoryRequest.class))).thenThrow(new GenericException("Category name is required."));

        GenericException exception = assertThrows(GenericException.class, () -> {
            categoryController.createCategory(new CategoryRequest());
        });

        assertEquals("Category name is required.", exception.getMessage());
    }
}