package com.cookbook.service;
import com.cookbook.domain.dto.CategoryDTO;
import com.cookbook.domain.dto.CategoryRequest;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.domain.entity.CategoryEntity;
import com.cookbook.domain.exception.ResourceNotFoundException;
import com.cookbook.domain.mapper.impl.CategoryMapperImpl;
import com.cookbook.domain.mapper.impl.RecipeMapperImpl;
import com.cookbook.filter.Filter;
import com.cookbook.repository.CategoryRepository;
import com.cookbook.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService = Mockito.spy(new CategoryServiceImpl());

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_getAllCategories(){
        List<CategoryEntity> categoryEntities = new ArrayList<>();
        var c1 = new CategoryEntity("Category name");
        categoryEntities.add(c1);
        Mockito.doReturn(categoryEntities).when(categoryRepository).findAllCategories();

        var out = categoryService.findAllCategories();

        Mockito.verify(categoryRepository).findAllCategories();
        Assertions.assertAll(
                () -> Assertions.assertEquals("Category name",out.get(0).getName())
        );

    }

    @Test
    void testFindCategoryById() {
        when(categoryRepository.findCategoryById(anyInt())).thenReturn(new CategoryEntity());
        CategoryDTO result = categoryService.findCategoryById(1);
        assertEquals(CategoryDTO.class, result.getClass());
    }

    @Test
    void testCreateCategory() {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Test Category");

        when(categoryRepository.createCategory(any(CategoryEntity.class))).thenReturn(new CategoryEntity());
        CategoryDTO result = categoryService.createCategory(categoryRequest);
        assertEquals(CategoryDTO.class, result.getClass());
    }

    @Test
    void testUpdateCategory() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("Updated Category");

        when(categoryRepository.findCategoryById(anyInt())).thenReturn(new CategoryEntity());

        when(categoryRepository.createCategory(any(CategoryEntity.class))).thenAnswer(invocation -> {
            CategoryEntity argument = invocation.getArgument(0);
            return argument;
        });

        CategoryDTO result = categoryService.updateCategory(1, categoryDTO);

        assertEquals(CategoryDTO.class, result.getClass());
        assertEquals("Updated Category", result.getName());
    }


    @Test
    void testDeleteCategory() {
        when(categoryRepository.deleteCategory(anyInt())).thenReturn(new CategoryEntity());
        CategoryDTO result = categoryService.deleteCategory(1);
        assertEquals(CategoryDTO.class, result.getClass());
    }

}