package com.cookbook.domain.mapper;

import com.cookbook.domain.dto.RatingDTO;
import com.cookbook.domain.dto.RatingRequest;
import com.cookbook.domain.entity.RatingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
public interface RatingMapper {
    RatingMapper INSTANCE = Mappers.getMapper(RatingMapper.class);

    RatingDTO ratingEntityToDto(RatingEntity entity);
    RatingEntity ratingDtoToEntity(RatingDTO dto);
    RatingEntity ratingRequestToEntity(RatingRequest ratingRequest);
}
