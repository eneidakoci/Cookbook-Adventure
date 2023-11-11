package com.cookbook.repository;

import com.cookbook.domain.entity.MemberEntity;
import com.cookbook.domain.entity.RatingEntity;
import com.cookbook.domain.entity.RecipeEntity;

import java.util.List;

public interface RatingRepository {
    List<RatingEntity> findAllRatings();
    RatingEntity findRatingById(Integer id);
    RatingEntity createRating(RatingEntity rating);
    RatingEntity updateRating(Integer id, RatingEntity rating);
    RatingEntity deleteRating(Integer id);
    MemberEntity findMemberByRatingId(Integer ratingId);
    RecipeEntity findRecipeByRatingId(Integer ratingId);
    List<RatingEntity> findRatingsByMember(MemberEntity memberEntity);

    List<RatingEntity> findRatingsByRecipeId(Integer recipeId);
}
