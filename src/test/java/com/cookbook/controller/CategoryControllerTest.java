package com.cookbook.controller;

import com.cookbook.CookbookAdventureApplication;
import com.cookbook.domain.dto.CategoryDTO;
import com.cookbook.domain.dto.CategoryRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {CookbookAdventureApplication.class})
public class CategoryControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void test_shouldReturnStatus200_OK() {
        ResponseEntity<CategoryDTO> response = restTemplate
                .getForEntity("/api/categories/{categoryId}", CategoryDTO.class, 5);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void test_shouldReturnCategoryResponseWhenCategoryIsFound_ok() {
        ResponseEntity<CategoryDTO> response = restTemplate
                .getForEntity("/api/categories/{categoryId}", CategoryDTO.class, 5);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        CategoryDTO categoryObject = response.getBody();
        categoryObject.setName("New Category");

        Assertions.assertAll(
                () -> Assertions.assertEquals(5, categoryObject.getCategoryId()),
                () -> Assertions.assertEquals("New Category", categoryObject.getName())
        );
    }

    @Test
    void test_shouldReturnStatus201WhenAddCategoryApiIsCalled() {
        var categoryRequest = new CategoryRequest("New Category");
        ResponseEntity<CategoryDTO> postResponse = restTemplate
                .postForEntity("/api/categories", categoryRequest, CategoryDTO.class);

        URI locationOfCategory = postResponse.getHeaders().getLocation();
        ResponseEntity<CategoryDTO> getResponse = restTemplate
                .getForEntity(locationOfCategory, CategoryDTO.class);
        var newCategoryCreated = getResponse.getBody();

        Assertions.assertAll(
                () -> Assertions.assertEquals(HttpStatus.CREATED, postResponse.getStatusCode()),
                () -> Assertions.assertEquals(HttpStatus.OK, getResponse.getStatusCode()),
                () -> {
                    assert newCategoryCreated != null;
                    Assertions.assertEquals("New Category", newCategoryCreated.getName());
                }
        );
    }

    @Test
    void test_shouldReturnStatus500WhenCategoryWithGivenIdNotFound() {
        CategoryDTO categoryDTO = new CategoryDTO(100, "Non-existent Category");
        ResponseEntity<Void> putResponse = restTemplate
                .exchange("/api/categories/{categoryId}", HttpMethod.PUT, new HttpEntity<>(categoryDTO), Void.class, 100);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, putResponse.getStatusCode());
    }


//    @Test
//    void test_shouldReturnStatus204DeletedWhenCategoryIsDeleted() {
//        ResponseEntity<Void> deleteResponse = restTemplate
//                .exchange("/api/categories/{categoryId}", HttpMethod.DELETE, null, Void.class, 2);
//        Assertions.assertEquals(deleteResponse.getStatusCode(), HttpStatus.NO_CONTENT);
//
//        //verify player with given id 2 is successfully deleted
//        ResponseEntity<CategoryDTO> getResponse = restTemplate
//                .getForEntity("/api/categories/{categoryId}", CategoryDTO.class, 2);
//        Assertions.assertEquals(getResponse.getStatusCode(), HttpStatus.NOT_FOUND);
//    }

}