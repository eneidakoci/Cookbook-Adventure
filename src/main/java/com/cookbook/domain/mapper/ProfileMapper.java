package com.cookbook.domain.mapper;

import com.cookbook.domain.dto.ProfileDTO;
import com.cookbook.domain.dto.ProfileRequest;
import com.cookbook.domain.entity.ProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface ProfileMapper {
    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    ProfileDTO profileEntityToDto(ProfileEntity entity);
    ProfileEntity userProfileDtoToEntity(ProfileDTO dto);
    ProfileEntity userRequestToEntity(ProfileRequest userProfileRequest);
}
