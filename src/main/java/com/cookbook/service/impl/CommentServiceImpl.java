package com.cookbook.service.impl;

import com.cookbook.domain.dto.CommentDTO;
import com.cookbook.domain.dto.CommentRequest;
import com.cookbook.domain.dto.MemberDTO;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.domain.entity.CommentEntity;
import com.cookbook.domain.entity.MemberEntity;
import com.cookbook.domain.entity.RecipeEntity;
import com.cookbook.domain.exception.ResourceNotFoundException;
import com.cookbook.domain.mapper.CommentMapper;
import com.cookbook.domain.mapper.MemberMapper;
import com.cookbook.domain.mapper.RecipeMapper;
import com.cookbook.filter.Filter;
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
    public List<CommentDTO> findAllComments(Filter...filters) {
        List<CommentEntity> comments = commentRepository.findAllComments(filters);
        return comments.stream()
                .map(CommentMapper::commentEntityToDto)
                .toList();
    }

    @Override
    public CommentDTO findCommentById(Integer id) throws ResourceNotFoundException {
        CommentEntity commentEntity = commentRepository.findCommentById(id);
        if(commentEntity == null){
            throw new ResourceNotFoundException("Comment does not exist.");
        }
        return CommentMapper.commentEntityToDto(commentEntity);
    }

    @Override
    public CommentDTO createComment(CommentRequest commentRequest) {
        CommentEntity commentEntity = CommentMapper.commentRequestToEntity(commentRequest);
        CommentEntity createdCommentEntity = commentRepository.createComment(commentEntity);
        return CommentMapper.commentEntityToDto(createdCommentEntity);
    }

    @Override
    public CommentDTO updateComment(Integer id, CommentRequest commentRequest) {
        CommentEntity existingCommentEntity = commentRepository.findCommentById(id);
        if (existingCommentEntity != null) {
            CommentEntity updatedCommentEntity = CommentMapper.commentRequestToEntity(commentRequest);
            updatedCommentEntity.setCommentId(id);
            updatedCommentEntity.setCreatedDate(existingCommentEntity.getCreatedDate());
            CommentEntity savedCommentEntity = commentRepository.updateComment(id, updatedCommentEntity);
            return CommentMapper.commentEntityToDto(savedCommentEntity);
        }
        return null;
    }

    @Override
    public CommentDTO deleteComment(Integer id) {
        try {
            CommentEntity deletedComment = commentRepository.deleteComment(id);
            return CommentMapper.commentEntityToDto(deletedComment);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Category not found with id: " + id);
        }
    }

    @Override
    public MemberDTO findMemberByCommentId(Integer commentId) {
        CommentEntity commentEntity = commentRepository.findCommentById(commentId);
        if (commentEntity != null) {
            MemberEntity memberEntity = commentEntity.getMemberEntity();
            return MemberMapper.memberEntityToDto(memberEntity);
        }
        return null;
    }

    @Override
    public RecipeDTO findRecipeByCommentId(Integer commentId) {
        CommentEntity commentEntity = commentRepository.findCommentById(commentId);
        if (commentEntity != null) {
            RecipeEntity recipeEntity = commentEntity.getRecipeEntity();
            return RecipeMapper.recipeEntityToDto(recipeEntity);
        }
        return null;
    }
}
