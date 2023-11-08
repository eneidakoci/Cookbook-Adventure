package com.cookbook.domain.mapper;

import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.domain.entity.RecipeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface RecipeMapper {
    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    RecipeDTO recipeEntityToDto(RecipeEntity entity);
    RecipeEntity recipeDtoToEntity(RecipeDTO dto);
}
