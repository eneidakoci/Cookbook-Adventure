package com.cookbook.repository;

import com.cookbook.domain.entity.CommentEntity;
import com.cookbook.domain.entity.MemberEntity;
import com.cookbook.domain.entity.RecipeEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CommentRepository {
    List<CommentEntity> findAllComments(Integer pageNumber, Integer pageSize);
    CommentEntity findCommentById(Integer id);
    CommentEntity createComment(CommentEntity comment);
    CommentEntity updateComment(Integer id, CommentEntity comment);
    CommentEntity deleteComment(Integer id);
    MemberEntity findMemberByCommentId(Integer commentId);
    RecipeEntity findRecipeByCommentId(Integer commentId);

    List<CommentEntity> findCommentsByMember(MemberEntity memberEntity);

    List<CommentEntity> findCommentsByRecipeId(Integer recipeId);
}
