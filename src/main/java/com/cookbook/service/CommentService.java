package com.cookbook.service;

import com.cookbook.domain.dto.CommentDTO;
import com.cookbook.domain.dto.CommentRequest;
import com.cookbook.domain.dto.MemberDTO;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.domain.entity.CommentEntity;

import java.util.List;

public interface CommentService {
    List<CommentDTO> findAllComments(Integer pageNumber, Integer pageSize);
    CommentDTO findCommentById(Integer id);
    CommentDTO createComment(CommentRequest comment);
    CommentDTO updateComment(Integer id, CommentRequest comment);
    CommentDTO deleteComment(Integer id);
    MemberDTO findMemberByCommentId(Integer commentId);
    RecipeDTO findRecipeByCommentId(Integer commentId);
}
