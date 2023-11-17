package com.cookbook.domain.mapper.impl;

import com.cookbook.domain.dto.CommentDTO;
import com.cookbook.domain.dto.CommentRequest;
import com.cookbook.domain.entity.CommentEntity;

public class CommentMapperImpl{
    public static CommentDTO commentEntityToDto(CommentEntity entity) {
        if (entity == null) {
            return null;
        }

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCreatedDate(entity.getCreatedDate());
        commentDTO.setLastModified(entity.getLastModified());
        commentDTO.setDeleted(entity.isDeleted());
        commentDTO.setCommentId(entity.getCommentId());
        commentDTO.setCommentText(entity.getCommentText());
        commentDTO.setMemberEntity(entity.getMemberEntity());
        commentDTO.setRecipeEntity(entity.getRecipeEntity());

        return commentDTO;
    }

    public static CommentEntity commentDtoToEntity(CommentDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setCreatedDate( dto.getCreatedDate() );
        commentEntity.setLastModified( dto.getLastModified() );
        commentEntity.setDeleted( dto.isDeleted() );
        commentEntity.setCommentId( dto.getCommentId() );
        commentEntity.setCommentText( dto.getCommentText() );
        commentEntity.setMemberEntity( dto.getMemberEntity() );
        commentEntity.setRecipeEntity( dto.getRecipeEntity() );

        return commentEntity;
    }

    public static CommentEntity commentRequestToEntity(CommentRequest commentRequest) {
        if ( commentRequest == null ) {
            return null;
        }

        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setCreatedDate( commentRequest.getCreatedDate() );
        commentEntity.setLastModified( commentRequest.getLastModified() );
        commentEntity.setDeleted( commentRequest.isDeleted() );
        commentEntity.setMemberEntity(commentRequest.getMemberEntity());
        commentEntity.setCommentText( commentRequest.getCommentText() );
        commentEntity.setRecipeEntity(commentRequest.getRecipeEntity());

        return commentEntity;
    }


}
