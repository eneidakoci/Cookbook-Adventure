package com.cookbook.controller;

import com.cookbook.aspect.MeasureTime;
import com.cookbook.configuration.AdminAccess;
import com.cookbook.configuration.UserAndAdminAccess;
import com.cookbook.domain.dto.MemberDTO;
import com.cookbook.domain.dto.RatingDTO;
import com.cookbook.domain.dto.RatingRequest;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.domain.exception.GenericException;
import com.cookbook.domain.exception.ResourceNotFoundException;
import com.cookbook.filter.Filter;
import com.cookbook.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @AdminAccess
    @MeasureTime
    @GetMapping
    public ResponseEntity<List<RatingDTO>> findAllRatings(@RequestParam Integer pageNumber,
                                                          @RequestParam Integer pageSize,
                                                          @RequestParam(required = false) String sort,
                                                          @RequestParam(required = false) String rate) {
        Filter rateFilter = new Filter("rate",rate,"LIKE", null, pageNumber, pageSize);
        if(sort != null){
            String[] sortValue = sort.split(":");
            if(Objects.equals(sortValue[0], "rate")){
                rateFilter.setSort(sortValue[1]);
            }else{
                throw new RuntimeException("Invalid sort field.");
            }
        }
        List<RatingDTO> ratingDTOS = ratingService.findAllRatings(rateFilter);
        if(ratingDTOS == null){
            throw new ResourceNotFoundException("ratings not found.");
        }
        return ResponseEntity.ok(ratingDTOS);
    }

    @AdminAccess
    @GetMapping("/{id}")
    public ResponseEntity<RatingDTO> findRatingById(@PathVariable Integer id) {
        RatingDTO rating = ratingService.findRatingById(id);
        if (rating != null) {
            return ResponseEntity.ok(rating);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @UserAndAdminAccess
    @PostMapping
    public ResponseEntity<RatingDTO> createRating(@RequestBody RatingRequest ratingRequest) {
        if(ratingRequest.getRate() == null ){
            throw new GenericException("Rating is required.");
        }
        RatingDTO createdRating = ratingService.createRating(ratingRequest);
        return ResponseEntity.ok(createdRating);
    }

    @UserAndAdminAccess
    @PutMapping("/{id}")
    public ResponseEntity<RatingDTO> updateRating(@PathVariable Integer id, @RequestBody RatingRequest ratingRequest) {
        RatingDTO updatedRating = ratingService.updateRating(id, ratingRequest);
        if (updatedRating != null) {
            return ResponseEntity.ok(updatedRating);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @UserAndAdminAccess
    @DeleteMapping("/{id}")
    public ResponseEntity<RatingDTO> deleteRating(@PathVariable Integer id) {
        RatingDTO deletedRating = ratingService.deleteRating(id);
        if (deletedRating != null) {
            return ResponseEntity.ok(deletedRating);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @UserAndAdminAccess
    @GetMapping("/{id}/member")
    public ResponseEntity<MemberDTO> findMemberByRatingId(@PathVariable Integer id) {
        MemberDTO member = ratingService.findMemberByRatingId(id);
        if (member != null) {
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @UserAndAdminAccess
    @GetMapping("/{id}/recipe")
    public ResponseEntity<RecipeDTO> findRecipeByRatingId(@PathVariable Integer id) {
        RecipeDTO recipe = ratingService.findRecipeByRatingId(id);
        if (recipe != null) {
            return ResponseEntity.ok(recipe);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}