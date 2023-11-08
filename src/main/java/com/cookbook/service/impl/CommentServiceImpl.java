package com.cookbook.service.impl;

import com.cookbook.domain.dto.CommentDTO;
import com.cookbook.domain.dto.CommentRequest;
import com.cookbook.domain.dto.MemberDTO;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.domain.entity.CommentEntity;
import com.cookbook.domain.entity.MemberEntity;
import com.cookbook.domain.entity.RecipeEntity;
import com.cookbook.domain.mapper.CommentMapper;
import com.cookbook.domain.mapper.MemberMapper;
import com.cookbook.domain.mapper.RecipeMapper;
import com.cookbook.repository.CommentRepository;
import com.cookbook.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private final CommentRepository commentRepository;
    @Autowired
    private final CommentMapper commentMapper;
    private MemberMapper memberMapper;
    private RecipeMapper recipeMapper;

    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public List<CommentDTO> findAllComments() {
        List<CommentEntity> comments = commentRepository.findAllComments();
        return comments.stream()
                .map(commentMapper::commentEntityToDto)
                .toList();
    }

    @Override
    public CommentDTO findCommentById(Integer id) {
        CommentEntity commentEntity = commentRepository.findCommentById(id);
        return commentMapper.commentEntityToDto(commentEntity);
    }

    @Override
    public CommentDTO createComment(CommentRequest commentRequest) {
        CommentEntity commentEntity = commentMapper.commentRequestToEntity(commentRequest);
        CommentEntity createdCommentEntity = commentRepository.createComment(commentEntity);
        return commentMapper.commentEntityToDto(createdCommentEntity);
    }

    @Override
    public CommentDTO updateComment(Integer id, CommentRequest commentRequest) {
        CommentEntity existingCommentEntity = commentRepository.findCommentById(id);
        if (existingCommentEntity != null) {
            CommentEntity updatedCommentEntity = commentMapper.commentRequestToEntity(commentRequest);
            updatedCommentEntity.setCommentId(id);
            updatedCommentEntity.setCreatedDate(existingCommentEntity.getCreatedDate());
            CommentEntity savedCommentEntity = commentRepository.updateComment(id, updatedCommentEntity);
            return commentMapper.commentEntityToDto(savedCommentEntity);
        }
        return null;
    }

    @Override
    public void deleteComment(Integer id) {
        commentRepository.deleteComment(id);
    }

    @Override
    public MemberDTO findMemberByCommentId(Integer commentId) {
        CommentEntity commentEntity = commentRepository.findCommentById(commentId);
        if (commentEntity != null) {
            MemberEntity memberEntity = commentEntity.getMemberEntity();
            return memberMapper.memberEntityToDto(memberEntity);
        }
        return null;
    }

    @Override
    public RecipeDTO findRecipeByCommentId(Integer commentId) {
        CommentEntity commentEntity = commentRepository.findCommentById(commentId);
        if (commentEntity != null) {
            RecipeEntity recipeEntity = commentEntity.getRecipeEntity();
            return recipeMapper.recipeEntityToDto(recipeEntity);
        }
        return null;
    }
}
