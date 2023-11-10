package com.cookbook.repository.impl;
import com.cookbook.domain.entity.MemberEntity;
import com.cookbook.domain.entity.RatingEntity;
import com.cookbook.domain.entity.RecipeEntity;
import com.cookbook.repository.RatingRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class RatingRepositoryImpl implements RatingRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private static final String SELECT_ALL_RATINGS = "SELECT r FROM RatingEntity r";
    private static final String SELECT_RATING_BY_ID = "SELECT r FROM RatingEntity r WHERE r.ratingId = :ratingId";
    private static final String SELECT_MEMBER_BY_RATING_ID = "SELECT r.memberEntity FROM RatingEntity r WHERE r.ratingId = :ratingId";
    private static final String SELECT_RECIPE_BY_RATING_ID = "SELECT r.recipeEntity FROM RatingEntity r WHERE r.ratingId = :ratingId";

    private static final String SELECT_RATINGS_BY_MEMBER = "SELECT r FROM RatingEntity r WHERE r.memberEntity = :memberEntity";
    @Override
    public List<RatingEntity> findAllRatings() {
        return entityManager.createQuery(SELECT_ALL_RATINGS, RatingEntity.class)
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
}
