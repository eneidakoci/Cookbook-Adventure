package com.cookbook.repository.impl;
import com.cookbook.domain.entity.CommentEntity;
import com.cookbook.domain.entity.MemberEntity;
import com.cookbook.domain.entity.RecipeEntity;
import com.cookbook.repository.CommentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepositoryImpl implements CommentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private static final String SELECT_ALL_COMMENTS = "SELECT c FROM CommentEntity c";
    @Override
    public List<CommentEntity> findAllComments() {
        return entityManager.createQuery(SELECT_ALL_COMMENTS, CommentEntity.class)
                .getResultList();
    }

    @Override
    public CommentEntity findCommentById(Integer id) {
        return entityManager.find(CommentEntity.class, id);
    }

    @Override
    public CommentEntity createComment(CommentEntity comment) {
        entityManager.merge(comment);
        return comment;
    }

    @Override
    public CommentEntity updateComment(Integer id, CommentEntity comment) {
        comment.setCommentId(id); // Set the ID of the comment to be updated
        return entityManager.merge(comment);
    }

    @Override
    public void deleteComment(Integer id) {
        CommentEntity comment = entityManager.find(CommentEntity.class, id);
        if (comment != null) {
            entityManager.remove(comment);
        }
    }

    @Override
    public MemberEntity findMemberByCommentId(Integer commentId) {
        CommentEntity comment = entityManager.find(CommentEntity.class, commentId);
        if (comment != null) {
            return comment.getMemberEntity();
        }
        return null;
    }

    @Override
    public RecipeEntity findRecipeByCommentId(Integer commentId) {
        CommentEntity comment = entityManager.find(CommentEntity.class, commentId);
        if (comment != null) {
            return comment.getRecipeEntity();
        }
        return null;
    }
}