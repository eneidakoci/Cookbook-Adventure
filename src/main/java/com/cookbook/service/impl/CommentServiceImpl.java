package com.cookbook.service.impl;

import com.cookbook.domain.dto.CommentDTO;
import com.cookbook.domain.dto.CommentRequest;
import com.cookbook.domain.dto.MemberDTO;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.domain.entity.CategoryEntity;
import com.cookbook.domain.entity.CommentEntity;
import com.cookbook.domain.entity.MemberEntity;
import com.cookbook.domain.entity.RecipeEntity;
import com.cookbook.domain.mapper.CommentMapper;
import com.cookbook.domain.mapper.MemberMapper;
import com.cookbook.domain.mapper.RecipeMapper;
import com.cookbook.domain.mapper.impl.CategoryMapperImpl;
import com.cookbook.domain.mapper.impl.CommentMapperImpl;
import com.cookbook.domain.mapper.impl.MemberMapperImpl;
import com.cookbook.domain.mapper.impl.RecipeMapperImpl;
import com.cookbook.repository.CommentRepository;
import com.cookbook.service.CommentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<CommentDTO> findAllComments() {
        List<CommentEntity> comments = commentRepository.findAllComments();
        return comments.stream()
                .map(CommentMapperImpl::commentEntityToDto)
                .toList();
    }

    @Override
    public CommentDTO findCommentById(Integer id) {
        CommentEntity commentEntity = commentRepository.findCommentById(id);
        return CommentMapperImpl.commentEntityToDto(commentEntity);
    }

    @Override
    public CommentDTO createComment(CommentRequest commentRequest) {
        CommentEntity commentEntity = CommentMapperImpl.commentRequestToEntity(commentRequest);
        CommentEntity createdCommentEntity = commentRepository.createComment(commentEntity);
        return CommentMapperImpl.commentEntityToDto(createdCommentEntity);
    }

    @Override
    public CommentDTO updateComment(Integer id, CommentRequest commentRequest) {
        CommentEntity existingCommentEntity = commentRepository.findCommentById(id);
        if (existingCommentEntity != null) {
            CommentEntity updatedCommentEntity = CommentMapperImpl.commentRequestToEntity(commentRequest);
            updatedCommentEntity.setCommentId(id);
            updatedCommentEntity.setCreatedDate(existingCommentEntity.getCreatedDate());
            CommentEntity savedCommentEntity = commentRepository.updateComment(id, updatedCommentEntity);
            return CommentMapperImpl.commentEntityToDto(savedCommentEntity);
        }
        return null;
    }

    @Override
    public CommentDTO deleteComment(Integer id) {
        try {
            CommentEntity deletedComment = commentRepository.deleteComment(id);
            return CommentMapperImpl.commentEntityToDto(deletedComment);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Category not found with id: " + id);
        }
    }

    @Override
    public MemberDTO findMemberByCommentId(Integer commentId) {
        CommentEntity commentEntity = commentRepository.findCommentById(commentId);
        if (commentEntity != null) {
            MemberEntity memberEntity = commentEntity.getMemberEntity();
            return MemberMapperImpl.memberEntityToDto(memberEntity);
        }
        return null;
    }

    @Override
    public RecipeDTO findRecipeByCommentId(Integer commentId) {
        CommentEntity commentEntity = commentRepository.findCommentById(commentId);
        if (commentEntity != null) {
            RecipeEntity recipeEntity = commentEntity.getRecipeEntity();
            return RecipeMapperImpl.recipeEntityToDto(recipeEntity);
        }
        return null;
    }
}
