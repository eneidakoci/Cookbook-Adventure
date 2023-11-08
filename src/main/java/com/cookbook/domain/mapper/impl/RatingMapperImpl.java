package com.cookbook.domain.mapper.impl;

import com.cookbook.domain.dto.RatingDTO;
import com.cookbook.domain.dto.RatingRequest;
import com.cookbook.domain.entity.RatingEntity;
import com.cookbook.domain.mapper.RatingMapper;

public class RatingMapperImpl implements RatingMapper {
    @Override
    public RatingDTO ratingEntityToDto(RatingEntity entity) {
        if(entity == null){
            return null;
        }
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setDeleted(entity.isDeleted());
        ratingDTO.setCreatedDate(entity.getCreatedDate());
        ratingDTO.setLastModified(entity.getLastModified());
        ratingDTO.setRecipeEntity(entity.getRecipeEntity());
        ratingDTO.setRatingId(entity.getRatingId());
        ratingDTO.setRate(entity.getRate());
        ratingDTO.setMemberEntity(entity.getMemberEntity());
        return ratingDTO;
    }

    @Override
    public RatingEntity ratingDtoToEntity(RatingDTO dto) {
        RatingEntity ratingEntity = new RatingEntity();
        ratingEntity.setDeleted(dto.isDeleted());
        ratingEntity.setCreatedDate(dto.getCreatedDate());
        ratingEntity.setLastModified(dto.getLastModified());
        ratingEntity.setRecipeEntity(dto.getRecipeEntity());
        ratingEntity.setRatingId(dto.getRatingId());
        ratingEntity.setRate(dto.getRate());
        ratingEntity.setMemberEntity(dto.getMemberEntity());
        return ratingEntity;
    }

    @Override
    public RatingEntity ratingRequestToEntity(RatingRequest ratingRequest) {
        RatingEntity rating = new RatingEntity();
        rating.setDeleted(ratingRequest.isDeleted());
        rating.setCreatedDate(ratingRequest.getCreatedDate());
        rating.setLastModified(ratingRequest.getLastModified());
        rating.setRate(ratingRequest.getRate());
        rating.setMemberEntity(ratingRequest.getMemberEntity());
        return rating;
    }
}
