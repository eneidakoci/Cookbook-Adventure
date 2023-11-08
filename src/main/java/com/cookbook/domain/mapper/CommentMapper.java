package com.cookbook.domain.mapper;

import com.cookbook.domain.dto.CommentDTO;
import com.cookbook.domain.dto.CommentRequest;
import com.cookbook.domain.entity.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentDTO commentEntityToDto(CommentEntity entity);
    CommentEntity commentDtoToEntity(CommentDTO dto);
    CommentEntity commentRequestToEntity(CommentRequest commentRequest);
}
