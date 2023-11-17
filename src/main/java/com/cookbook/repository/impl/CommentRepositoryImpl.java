package com.cookbook.repository.impl;
import com.cookbook.domain.entity.CategoryEntity;
import com.cookbook.domain.entity.CommentEntity;
import com.cookbook.domain.entity.MemberEntity;
import com.cookbook.domain.entity.RecipeEntity;
import com.cookbook.filter.Filter;
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

    private static final String SELECT_ALL_COMMENTS = "SELECT c FROM CommentEntity c WHERE 1=1 ";
    private static final String SELECT_COMMENTS_BY_MEMBER = "SELECT c FROM CommentEntity c WHERE c.memberEntity = :memberEntity";
    private static final String SELECT_COMMENTS_BY_RECIPE ="SELECT c FROM CommentEntity c WHERE c.recipeEntity.recipeId = :recipeId";
    @Override
    public List<CommentEntity> findAllComments(Filter...filters) {
        String dynamicQuery = SELECT_ALL_COMMENTS;

        if (filters != null) {
            if (filters[0].getValue() != null) {
                dynamicQuery += "AND c." + filters[0].getField() + " " +
                        filters[0].getOperator() + " '%" + filters[0].getValue() + "%' ";
            }
            if (filters[0].getSort() != null) {
                dynamicQuery += "ORDER BY c." + filters[0].getField() + " " + filters[0].getSort();
            }
            if (filters[0].getPageSize() != null && filters[0].getPageNumber() != null) {
                return entityManager.createQuery(dynamicQuery, CommentEntity.class)
                        .setFirstResult((filters[0].getPageNumber() - 1) * filters[0].getPageSize())
                        .setMaxResults(filters[0].getPageSize())
                        .getResultList();
            }
        }

        return entityManager.createQuery(dynamicQuery, CommentEntity.class)
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
        } else {
            throw new EntityNotFoundException("Comment with ID " + id + " not found");
        }
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