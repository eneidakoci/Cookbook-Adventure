package com.cookbook.domain.mapper;

import com.cookbook.domain.dto.UserDTO;
import com.cookbook.domain.entity.CommentEntity;
import com.cookbook.domain.entity.UserEntity;

public class UserMapper {
    public static UserDTO userEntityToDto(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(entity.getUsername());
        userDTO.setUserId(entity.getUserId());
        userDTO.setAuthorities(entity.getAuthorities());

        return userDTO;
    }

    public static UserEntity userDtoToEntity(UserDTO dto) {
        if ( dto == null ) {
            return null;
        }
        UserEntity entity = new UserEntity();
        entity.setUsername(dto.getUsername());
        entity.setUserId(dto.getUserId());
        entity.setAuthorities(dto.getAuthorities());

        return entity;

    }
}
