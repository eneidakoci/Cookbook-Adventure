package com.cookbook.controller;

import com.cookbook.domain.dto.MemberDTO;
import com.cookbook.domain.dto.RatingDTO;
import com.cookbook.domain.dto.RatingRequest;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping
    public ResponseEntity<List<RatingDTO>> findAllRatings(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        List<RatingDTO> ratings = ratingService.findAllRatings(pageNumber, pageSize);
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingDTO> findRatingById(@PathVariable Integer id) {
        RatingDTO rating = ratingService.findRatingById(id);
        if (rating != null) {
            return ResponseEntity.ok(rating);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<RatingDTO> createRating(@RequestBody RatingRequest ratingRequest) {
        RatingDTO createdRating = ratingService.createRating(ratingRequest);
        return ResponseEntity.ok(createdRating);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RatingDTO> updateRating(@PathVariable Integer id, @RequestBody RatingRequest ratingRequest) {
        RatingDTO updatedRating = ratingService.updateRating(id, ratingRequest);
        if (updatedRating != null) {
            return ResponseEntity.ok(updatedRating);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RatingDTO> deleteRating(@PathVariable Integer id) {
        RatingDTO deletedRating = ratingService.deleteRating(id);
        if (deletedRating != null) {
            return ResponseEntity.ok(deletedRating);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/member")
    public ResponseEntity<MemberDTO> findMemberByRatingId(@PathVariable Integer id) {
        MemberDTO member = ratingService.findMemberByRatingId(id);
        if (member != null) {
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

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