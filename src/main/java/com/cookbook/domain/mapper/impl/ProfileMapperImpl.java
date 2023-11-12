//package com.cookbook.domain.mapper.impl;
//
//import com.cookbook.domain.dto.ProfileDTO;
//import com.cookbook.domain.dto.ProfileRequest;
//import com.cookbook.domain.entity.ProfileEntity;
//import com.cookbook.domain.mapper.ProfileMapper;
//import org.springframework.stereotype.Component;
//
//public class ProfileMapperImpl {
//    public static ProfileDTO profileEntityToDto(ProfileEntity entity) {
//        if ( entity == null ) {
//            return null;
//        }
//
//        ProfileDTO profileDTO = new ProfileDTO();
//
//        profileDTO.setCreatedDate( entity.getCreatedDate() );
//        profileDTO.setLastModified( entity.getLastModified() );
//        profileDTO.setDeleted( entity.isDeleted() );
//        profileDTO.setProfileId( entity.getProfileId() );
//        profileDTO.setProfileLink( entity.getProfileLink() );
//
//        return profileDTO;
//    }
//
//    public static ProfileEntity userProfileDtoToEntity(ProfileDTO dto) {
//        if ( dto == null ) {
//            return null;
//        }
//
//        ProfileEntity profileEntity = new ProfileEntity();
//
//        profileEntity.setCreatedDate( dto.getCreatedDate() );
//        profileEntity.setLastModified( dto.getLastModified() );
//        profileEntity.setDeleted( dto.isDeleted() );
//        profileEntity.setProfileId( dto.getProfileId() );
//        profileEntity.setProfileLink( dto.getProfileLink() );
//
//        return profileEntity;
//    }
//
//    public static ProfileEntity userRequestToEntity(ProfileRequest userProfileRequest) {
//        if ( userProfileRequest == null ) {
//            return null;
//        }
//
//        ProfileEntity profileEntity = new ProfileEntity();
//
//        profileEntity.setCreatedDate( userProfileRequest.getCreatedDate() );
//        profileEntity.setLastModified( userProfileRequest.getLastModified() );
//        profileEntity.setDeleted( userProfileRequest.isDeleted() );
//        profileEntity.setProfileLink( userProfileRequest.getProfileLink() );
//
//        return profileEntity;
//    }
//
//}
