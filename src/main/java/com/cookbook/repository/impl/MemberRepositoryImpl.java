package com.cookbook.repository.impl;

import com.cookbook.domain.entity.CommentEntity;
import com.cookbook.domain.entity.MemberEntity;
import com.cookbook.domain.entity.RatingEntity;
import com.cookbook.domain.entity.RecipeEntity;
import com.cookbook.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class MemberRepositoryImpl implements MemberRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private static final String SELECT_ALL_MEMBERS = "SELECT m FROM MemberEntity m";
    private static final String SELECT_MEMBER_BY_ID = "SELECT m FROM MemberEntity m WHERE m.memberId = :memberId";
    private static final String SELECT_RECIPES_BY_MEMBER_ID = "SELECT r FROM RecipeEntity r WHERE r.memberEntity.memberId = :memberId";
    private static final String SELECT_COMMENTS_BY_MEMBER_ID = "SELECT c FROM CommentEntity c WHERE c.memberEntity.memberId = :memberId";
    private static final String SELECT_RATINGS_BY_MEMBER_ID = "SELECT r FROM RatingEntity r WHERE r.memberEntity.memberId = :memberId";

    @Override
    public List<MemberEntity> findAllMembers(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return entityManager.createQuery(SELECT_ALL_MEMBERS, MemberEntity.class)
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public MemberEntity findMemberById(Integer id) {
        return entityManager.createQuery(SELECT_MEMBER_BY_ID, MemberEntity.class)
                .setParameter("memberId", id)
                .getSingleResult();
    }
    @Transactional
    @Override
    public MemberEntity createMember(MemberEntity member) {
        entityManager.persist(member);
        return member;
    }
    @Transactional
    @Override
    public MemberEntity updateMember(Integer id, MemberEntity member) {
        member.setMemberId(id);
        member.setLastModified(LocalDateTime.now());
        return entityManager.merge(member);
    }
    @Transactional
    @Override
    public MemberEntity deleteMember(Integer id) {
        MemberEntity member = entityManager.find(MemberEntity.class, id);
        if (member != null) {
            entityManager.remove(member);
        }
        return member;
    }

    @Override
    public List<RecipeEntity> findRecipesByMemberId(Integer memberId) {
        return entityManager.createQuery(SELECT_RECIPES_BY_MEMBER_ID, RecipeEntity.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    @Override
    public List<CommentEntity> findCommentsByMemberId(Integer memberId) {
        return entityManager.createQuery(SELECT_COMMENTS_BY_MEMBER_ID, CommentEntity.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    @Override
    public List<RatingEntity> findRatingsByMemberId(Integer memberId) {
        return entityManager.createQuery(SELECT_RATINGS_BY_MEMBER_ID, RatingEntity.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

}