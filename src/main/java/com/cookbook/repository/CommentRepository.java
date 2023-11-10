package com.cookbook.repository;

import com.cookbook.domain.entity.CommentEntity;
import com.cookbook.domain.entity.MemberEntity;
import com.cookbook.domain.entity.RecipeEntity;

import java.util.List;

public interface CommentRepository {
    List<CommentEntity> findAllComments();
    CommentEntity findCommentById(Integer id);
    CommentEntity createComment(CommentEntity comment);
    CommentEntity updateComment(Integer id, CommentEntity comment);
    CommentEntity deleteComment(Integer id);
    MemberEntity findMemberByCommentId(Integer commentId);
    RecipeEntity findRecipeByCommentId(Integer commentId);

    List<CommentEntity> findCommentsByMember(MemberEntity memberEntity);
}
