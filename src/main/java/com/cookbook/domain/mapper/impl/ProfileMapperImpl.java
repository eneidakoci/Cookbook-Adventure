package com.cookbook.domain.mapper.impl;

import com.cookbook.domain.dto.ProfileDTO;
import com.cookbook.domain.dto.ProfileRequest;
import com.cookbook.domain.entity.ProfileEntity;
import com.cookbook.domain.mapper.ProfileMapper;

public class ProfileMapperImpl implements ProfileMapper {
    @Override
    public ProfileDTO profileEntityToDto(ProfileEntity entity) {
        if (entity == null) {
            return null;
        }
        ProfileDTO userProfileDTO = new ProfileDTO();
        userProfileDTO.setDeleted(entity.isDeleted());
        userProfileDTO.setCreatedDate(entity.getCreatedDate());
        userProfileDTO.setLastModified(entity.getLastModified());
        userProfileDTO.setProfileId(entity.getProfileId());
        userProfileDTO.setProfileLink(entity.getProfileLink());
        return userProfileDTO;
    }

    @Override
    public ProfileEntity userProfileDtoToEntity(ProfileDTO dto) {
        if (dto == null) {
            return null;
        }
        ProfileEntity userProfileEntity = new ProfileEntity();
        userProfileEntity.setDeleted(dto.isDeleted());
        userProfileEntity.setCreatedDate(dto.getCreatedDate());
        userProfileEntity.setLastModified(dto.getLastModified());
        userProfileEntity.setProfileId(dto.getProfileId());
        userProfileEntity.setProfileLink(dto.getProfileLink());
        return userProfileEntity;
    }

    @Override
    public ProfileEntity userRequestToEntity(ProfileRequest userProfileRequest) {
        ProfileEntity userProfile = new ProfileEntity();
        userProfile.setDeleted(userProfileRequest.isDeleted());
        userProfile.setCreatedDate(userProfileRequest.getCreatedDate());
        userProfile.setLastModified(userProfileRequest.getLastModified());
        userProfile.setProfileLink(userProfileRequest.getProfileLink());
        return userProfile;
    }

}
