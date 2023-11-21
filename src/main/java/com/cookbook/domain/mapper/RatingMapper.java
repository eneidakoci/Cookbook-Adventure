package com.cookbook.domain.mapper;

import com.cookbook.domain.dto.RatingDTO;
import com.cookbook.domain.dto.RatingRequest;
import com.cookbook.domain.entity.RatingEntity;

public class RatingMapper {

    public static RatingDTO ratingEntityToDto(RatingEntity entity) {
        if ( entity == null ) {
            return null;
        }

        RatingDTO ratingDTO = new RatingDTO();

        ratingDTO.setCreatedDate( entity.getCreatedDate() );
        ratingDTO.setLastModified( entity.getLastModified() );
        ratingDTO.setDeleted( entity.isDeleted() );
        ratingDTO.setRatingId( entity.getRatingId() );
        ratingDTO.setMemberEntity( entity.getMemberEntity() );
        ratingDTO.setRecipeEntity( entity.getRecipeEntity() );
        ratingDTO.setRate( entity.getRate() );

        return ratingDTO;
    }


    public static RatingEntity ratingDtoToEntity(RatingDTO dto) {
        if ( dto == null ) {
            return null;
        }

        RatingEntity ratingEntity = new RatingEntity();

        ratingEntity.setCreatedDate( dto.getCreatedDate() );
        ratingEntity.setLastModified( dto.getLastModified() );
        ratingEntity.setDeleted( dto.isDeleted() );
        ratingEntity.setRatingId( dto.getRatingId() );
        ratingEntity.setMemberEntity( dto.getMemberEntity() );
        ratingEntity.setRecipeEntity( dto.getRecipeEntity() );
        if ( dto.getRate() != null ) {
            ratingEntity.setRate( dto.getRate() );
        }

        return ratingEntity;
    }

    public static RatingEntity ratingRequestToEntity(RatingRequest ratingRequest) {
        if ( ratingRequest == null ) {
            return null;
        }

        RatingEntity ratingEntity = new RatingEntity();

        ratingEntity.setCreatedDate( ratingRequest.getCreatedDate() );
        ratingEntity.setLastModified( ratingRequest.getLastModified() );
        ratingEntity.setDeleted( ratingRequest.isDeleted() );
        ratingEntity.setMemberEntity( ratingRequest.getMemberEntity() );
        ratingEntity.setRecipeEntity(ratingRequest.getRecipeEntity());
        if ( ratingRequest.getRate() != null ) {
            ratingEntity.setRate( ratingRequest.getRate() );
        }

        return ratingEntity;
    }
}
