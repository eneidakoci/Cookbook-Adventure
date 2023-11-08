package com.cookbook.domain.mapper.impl;

import com.cookbook.domain.dto.CategoryRequest;
import com.cookbook.domain.dto.CommentDTO;
import com.cookbook.domain.dto.CommentRequest;
import com.cookbook.domain.entity.CategoryEntity;
import com.cookbook.domain.entity.CommentEntity;
import com.cookbook.domain.mapper.CommentMapper;

public class CommentMapperImpl implements CommentMapper {
    @Override
    public CommentDTO commentEntityToDto(CommentEntity entity) {
        if(entity == null){
            return null;
        }
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentId(entity.getCommentId());
        commentDTO.setCommentText(entity.getCommentText());
        commentDTO.setDeleted(entity.isDeleted());
        commentDTO.setCreatedDate(entity.getCreatedDate());
        commentDTO.setLastModified(entity.getLastModified());
        commentDTO.setRecipeEntity(entity.getRecipeEntity());
        return commentDTO;
    }

    @Override
    public CommentEntity commentDtoToEntity(CommentDTO dto) {
        if(dto == null){
            return null;
        }
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentId(dto.getCommentId());
        commentEntity.setCommentText(dto.getCommentText());
        commentEntity.setDeleted(dto.isDeleted());
        commentEntity.setCreatedDate(dto.getCreatedDate());
        commentEntity.setLastModified(dto.getLastModified());
        commentEntity.setRecipeEntity(dto.getRecipeEntity());
        return commentEntity;
    }

    @Override
    public CommentEntity commentRequestToEntity(CommentRequest commentRequest) {
        CommentEntity comment = new CommentEntity();
        comment.setCommentText(commentRequest.getCommentText());
        comment.setDeleted(commentRequest.isDeleted());
        comment.setCreatedDate(commentRequest.getCreatedDate());
        comment.setLastModified(commentRequest.getLastModified());
        return comment;
    }


}
