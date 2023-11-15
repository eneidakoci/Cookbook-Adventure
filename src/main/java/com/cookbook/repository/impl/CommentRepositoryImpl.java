package com.cookbook.repository.impl;
import com.cookbook.domain.entity.CommentEntity;
import com.cookbook.domain.entity.MemberEntity;
import com.cookbook.domain.entity.RecipeEntity;
import com.cookbook.repository.CommentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class CommentRepositoryImpl implements CommentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private static final String SELECT_ALL_COMMENTS = "SELECT c FROM CommentEntity c";
    private static final String SELECT_COMMENTS_BY_MEMBER = "SELECT c FROM CommentEntity c WHERE c.memberEntity = :memberEntity";
    private static final String SELECT_COMMENTS_BY_RECIPE ="SELECT c FROM CommentEntity c WHERE c.recipeEntity.recipeId = :recipeId";
    @Override
    public List<CommentEntity> findAllComments(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return entityManager.createQuery(SELECT_ALL_COMMENTS, CommentEntity.class)
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public CommentEntity findCommentById(Integer id) {
        return entityManager.find(CommentEntity.class, id);
    }

    @Transactional
    @Override
    public CommentEntity createComment(CommentEntity comment) {
        entityManager.persist(comment);
        comment.setCreatedDate(LocalDateTime.now());
        comment.setLastModified(LocalDateTime.now());
        return comment;
    }
    @Transactional
    @Override
    public CommentEntity updateComment(Integer id, CommentEntity comment) {
        comment.setCommentId(id); // Set the ID of the comment to be updated
        comment.setLastModified(LocalDateTime.now());
        return entityManager.merge(comment);
    }
    @Transactional
    @Override
    public CommentEntity deleteComment(Integer id) {
        CommentEntity comment = entityManager.find(CommentEntity.class, id);
        if (comment != null) {
            entityManager.remove(comment);
            comment.setDeleted(true);
            return comment;
        }else {
            throw new EntityNotFoundException("Comment with ID " + id + " not found");
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

    @Override
    public List<CommentEntity> findCommentsByMember(MemberEntity memberEntity) {
            return entityManager.createQuery(SELECT_COMMENTS_BY_MEMBER, CommentEntity.class)
                    .setParameter("memberEntity", memberEntity)
                    .getResultList();

    }

    @Override
    public List<CommentEntity> findCommentsByRecipeId(Integer recipeId) {
        return entityManager.createQuery(SELECT_COMMENTS_BY_RECIPE, CommentEntity.class)
                .setParameter("recipeId", recipeId)
                .getResultList();
    }
}