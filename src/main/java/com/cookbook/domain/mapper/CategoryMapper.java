package com.cookbook.domain.mapper;


import com.cookbook.domain.dto.CategoryDTO;
import com.cookbook.domain.dto.CategoryRequest;
import com.cookbook.domain.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryEntityToDto(CategoryEntity entity);
    CategoryEntity categoryDtoToEntity(CategoryDTO dto);

    CategoryEntity categoryRequestToEntity(CategoryRequest categoryRequest);
}
