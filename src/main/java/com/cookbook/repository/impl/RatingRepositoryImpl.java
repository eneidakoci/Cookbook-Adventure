package com.cookbook.repository.impl;
import com.cookbook.domain.entity.MemberEntity;
import com.cookbook.domain.entity.RatingEntity;
import com.cookbook.domain.entity.RecipeEntity;
import com.cookbook.filter.Filter;
import com.cookbook.repository.RatingRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class RatingRepositoryImpl implements RatingRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private static final String SELECT_ALL_RATINGS = "SELECT r FROM RatingEntity r WHERE 1=1 ";
    private static final String SELECT_RATING_BY_ID = "SELECT r FROM RatingEntity r WHERE r.ratingId = :ratingId";
    private static final String SELECT_MEMBER_BY_RATING_ID = "SELECT r.memberEntity FROM RatingEntity r WHERE r.ratingId = :ratingId";
    private static final String SELECT_RECIPE_BY_RATING_ID = "SELECT r.recipeEntity FROM RatingEntity r WHERE r.ratingId = :ratingId";

    private static final String SELECT_RATINGS_BY_MEMBER = "SELECT r FROM RatingEntity r WHERE r.memberEntity = :memberEntity";
    @Override
    public List<RatingEntity> findAllRatings(Filter...filters) {
        String dynamicQuery = SELECT_ALL_RATINGS;

        if (filters != null) {
            if (filters[0].getValue() != null) {
                dynamicQuery += "AND r." + filters[0].getField() + " " +
                        filters[0].getOperator() + " '%" + filters[0].getValue() + "%' ";
            }
            if (filters[0].getSort() != null) {
                dynamicQuery += "ORDER BY r." + filters[0].getField() + " " + filters[0].getSort();
            }
            if (filters[0].getPageSize() != null && filters[0].getPageNumber() != null) {
                return entityManager.createQuery(dynamicQuery, RatingEntity.class)
                        .setFirstResult((filters[0].getPageNumber() - 1) * filters[0].getPageSize())
                        .setMaxResults(filters[0].getPageSize())
                        .getResultList();
            }
        }
        return entityManager.createQuery(dynamicQuery, RatingEntity.class)
                .getResultList();
    }

    @Override
    public RatingEntity findRatingById(Integer id) {
        return entityManager.createQuery(SELECT_RATING_BY_ID, RatingEntity.class)
                .setParameter("ratingId", id)
                .getSingleResult();
    }
    @Transactional
    @Override
    public RatingEntity createRating(RatingEntity rating) {
        entityManager.persist(rating);
        rating.setCreatedDate(LocalDateTime.now());
        rating.setLastModified(LocalDateTime.now());
        return rating;
    }
    @Transactional
    @Override
    public RatingEntity updateRating(Integer id, RatingEntity rating) {
        rating.setRatingId(id);
        rating.setLastModified(LocalDateTime.now());
        return entityManager.merge(rating);
    }
    @Transactional
    @Override
    public RatingEntity deleteRating(Integer id) {
        RatingEntity rating = entityManager.find(RatingEntity.class, id);
        if(rating != null){
            entityManager.remove(rating);
            rating.setDeleted(true);
            return rating;
        }else {
            throw new EntityNotFoundException("Rating with ID " + id + " not found");
        }
    }

    @Override
    public MemberEntity findMemberByRatingId(Integer ratingId) {
        return entityManager.createQuery(SELECT_MEMBER_BY_RATING_ID, MemberEntity.class)
                .setParameter("ratingId", ratingId)
                .getSingleResult();
    }

    @Override
    public RecipeEntity findRecipeByRatingId(Integer ratingId) {
        return entityManager.createQuery(SELECT_RECIPE_BY_RATING_ID, RecipeEntity.class)
                .setParameter("ratingId", ratingId)
                .getSingleResult();
    }

    @Override
    public List<RatingEntity> findRatingsByMember(MemberEntity memberEntity) {
        return entityManager.createQuery(SELECT_RATINGS_BY_MEMBER, RatingEntity.class)
                .setParameter("memberEntity", memberEntity)
                .getResultList();
    }

    @Override
    public List<RatingEntity> findRatingsByRecipeId(Integer recipeId) {
        return entityManager.createQuery("SELECT r FROM RatingEntity r WHERE r.recipeEntity.recipeId = :recipeId", RatingEntity.class)
                .setParameter("recipeId", recipeId)
                .getResultList();
    }
}
