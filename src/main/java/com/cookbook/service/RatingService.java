package com.cookbook.service;

import com.cookbook.domain.dto.MemberDTO;
import com.cookbook.domain.dto.RatingDTO;
import com.cookbook.domain.dto.RatingRequest;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.domain.entity.MemberEntity;
import com.cookbook.domain.entity.RatingEntity;
import com.cookbook.domain.entity.RecipeEntity;

import java.util.List;

public interface RatingService {
    List<RatingDTO> findAllRatings();
    RatingDTO findRatingById(Integer id);
    RatingDTO createRating(RatingRequest rating);
    RatingDTO updateRating(Integer id, RatingRequest rating);
    RatingDTO deleteRating(Integer id);
    MemberDTO findMemberByRatingId(Integer ratingId);
    RecipeDTO findRecipeByRatingId(Integer ratingId);
}
