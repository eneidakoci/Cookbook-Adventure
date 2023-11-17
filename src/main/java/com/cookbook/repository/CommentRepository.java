package com.cookbook.repository;

import com.cookbook.domain.entity.CommentEntity;
import com.cookbook.domain.entity.MemberEntity;
import com.cookbook.domain.entity.RecipeEntity;
import com.cookbook.filter.Filter;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CommentRepository {
    List<CommentEntity> findAllComments(Filter...filters);
    CommentEntity findCommentById(Integer id);
    CommentEntity createComment(CommentEntity comment);
    CommentEntity updateComment(Integer id, CommentEntity comment);
    CommentEntity deleteComment(Integer id);

    List<CommentEntity> findCommentsByMember(MemberEntity memberEntity);

    List<CommentEntity> findCommentsByRecipeId(Integer recipeId);
}
