package com.cookbook.service.impl;

import com.cookbook.domain.dto.MemberDTO;
import com.cookbook.domain.dto.RatingDTO;
import com.cookbook.domain.dto.RatingRequest;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.domain.entity.MemberEntity;
import com.cookbook.domain.entity.RatingEntity;
import com.cookbook.domain.entity.RecipeEntity;
import com.cookbook.domain.exception.ResourceNotFoundException;
import com.cookbook.domain.mapper.MemberMapper;
import com.cookbook.domain.mapper.RatingMapper;
import com.cookbook.domain.mapper.RecipeMapper;
import com.cookbook.filter.Filter;
import com.cookbook.repository.RatingRepository;
import com.cookbook.service.RatingService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public List<RatingDTO> findAllRatings(Filter...filters) {
        List<RatingEntity> ratings = ratingRepository.findAllRatings(filters);
        return ratings.stream().map(RatingMapper::ratingEntityToDto).toList();
    }

    @Override
    public RatingDTO findRatingById(Integer id) throws ResourceNotFoundException{
        RatingEntity ratingEntity = ratingRepository.findRatingById(id);
        if(ratingEntity == null){
            throw new ResourceNotFoundException("Rating does not exist.");
        }
        return RatingMapper.ratingEntityToDto(ratingEntity);
    }

    @Override
    public RatingDTO createRating(RatingRequest ratingRequest) {
        RatingEntity ratingEntity = RatingMapper.ratingRequestToEntity(ratingRequest);
        RatingEntity savedRating = ratingRepository.createRating(ratingEntity);
        return RatingMapper.ratingEntityToDto(savedRating);
    }

    @Override
    public RatingDTO updateRating(Integer id, RatingRequest ratingRequest) {
        RatingEntity existingRatingOptional = ratingRepository.findRatingById(id);

        if (existingRatingOptional != null) {
            RatingEntity updatedRatingEntity = RatingMapper.ratingRequestToEntity(ratingRequest);
            updatedRatingEntity.setRatingId(id);
            RatingEntity savedRatingEntity = ratingRepository.updateRating(id, updatedRatingEntity);
            return RatingMapper.ratingEntityToDto(savedRatingEntity);
        }
        return null;
    }

    @Override
    public RatingDTO deleteRating(Integer id) {
        try {
            RatingEntity deleteRating = ratingRepository.deleteRating(id);
            return RatingMapper.ratingEntityToDto(deleteRating);
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
                return MemberMapper.memberEntityToDto(memberEntity);
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
                return RecipeMapper.recipeEntityToDto(recipeEntity);
            }
        }

        return null;
    }
}
