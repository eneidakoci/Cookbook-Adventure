package com.cookbook.repository;

import com.cookbook.domain.entity.CommentEntity;
import com.cookbook.domain.entity.MemberEntity;
import com.cookbook.domain.entity.RatingEntity;
import com.cookbook.domain.entity.RecipeEntity;

import java.util.List;

public interface MemberRepository {
    List<MemberEntity> findAllMembers();
    MemberEntity findMemberById(Integer id);
    MemberEntity createMember(MemberEntity member);
    MemberEntity updateMember(Integer id, MemberEntity member);
    void deleteMember(Integer id);
    List<RecipeEntity> findRecipesByMemberId(Integer memberId);
    List<CommentEntity> findCommentsByMemberId(Integer memberId);
    List<RatingEntity> findRatingsByMemberId(Integer memberId);
}
