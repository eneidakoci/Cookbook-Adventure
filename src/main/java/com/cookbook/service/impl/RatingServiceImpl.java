package com.cookbook.service.impl;

import com.cookbook.domain.dto.MemberDTO;
import com.cookbook.domain.dto.RatingDTO;
import com.cookbook.domain.dto.RatingRequest;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.domain.entity.CommentEntity;
import com.cookbook.domain.entity.MemberEntity;
import com.cookbook.domain.entity.RatingEntity;
import com.cookbook.domain.entity.RecipeEntity;
import com.cookbook.domain.mapper.RatingMapper;
import com.cookbook.domain.mapper.impl.MemberMapperImpl;
import com.cookbook.domain.mapper.impl.RatingMapperImpl;
import com.cookbook.domain.mapper.impl.RecipeMapperImpl;
import com.cookbook.repository.MemberRepository;
import com.cookbook.repository.RatingRepository;
import com.cookbook.repository.RecipeRepository;
import com.cookbook.service.RatingService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private RecipeRepository recipeRepository;


    @Override
    public List<RatingDTO> findAllRatings() {
        List<RatingEntity> ratings = ratingRepository.findAllRatings();
        return ratings.stream().map(RatingMapperImpl::ratingEntityToDto).toList();
    }

    @Override
    public RatingDTO findRatingById(Integer id) {
        RatingEntity ratingEntity = ratingRepository.findRatingById(id);
        return RatingMapperImpl.ratingEntityToDto(ratingEntity);
    }

    @Override
    public RatingDTO createRating(RatingRequest ratingRequest) {
        RatingEntity ratingEntity = RatingMapperImpl.ratingRequestToEntity(ratingRequest);
        RatingEntity savedRating = ratingRepository.createRating(ratingEntity);
        return RatingMapperImpl.ratingEntityToDto(savedRating);
    }

    @Override
    public RatingDTO updateRating(Integer id, RatingRequest ratingRequest) {
        RatingEntity existingRatingOptional = ratingRepository.findRatingById(id);

        if (existingRatingOptional != null) {
            RatingEntity updatedRatingEntity = RatingMapperImpl.ratingRequestToEntity(ratingRequest);
            updatedRatingEntity.setRatingId(id);
            RatingEntity savedRatingEntity = ratingRepository.updateRating(id, updatedRatingEntity);
            return RatingMapperImpl.ratingEntityToDto(savedRatingEntity);
        }
        return null;
    }

    @Override
    public RatingDTO deleteRating(Integer id) {
        try {
            RatingEntity deleteRating = ratingRepository.deleteRating(id);
            return RatingMapperImpl.ratingEntityToDto(deleteRating);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Rating not found with id: " + id);
        }
    }

    @Override
    public MemberDTO findMemberByRatingId(Integer ratingId) {
        RatingEntity ratingEntity = ratingRepository.findRatingById(ratingId);

        if (ratingEntity != null) {
            MemberEntity memberEntity = ratingEntity.getMemberEntity();
            if (memberEntity != null) {
                return MemberMapperImpl.memberEntityToDto(memberEntity);
            }
        }
        return null;
    }

    @Override
    public RecipeDTO findRecipeByRatingId(Integer ratingId) {
        RatingEntity ratingEntity = ratingRepository.findRatingById(ratingId);

        if (ratingEntity != null) {
            RecipeEntity recipeEntity = ratingEntity.getRecipeEntity();
            if (recipeEntity != null) {
                return RecipeMapperImpl.recipeEntityToDto(recipeEntity);
            }
        }

        return null;
    }
}
