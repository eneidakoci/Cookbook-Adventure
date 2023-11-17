package com.cookbook.repository;

import com.cookbook.domain.entity.CommentEntity;
import com.cookbook.domain.entity.MemberEntity;
import com.cookbook.domain.entity.RatingEntity;
import com.cookbook.domain.entity.RecipeEntity;
import com.cookbook.filter.Filter;

import java.util.List;

public interface MemberRepository {
    List<MemberEntity> findAllMembers(Filter...filters);
    MemberEntity findMemberById(Integer id);
    MemberEntity createMember(MemberEntity member);
    MemberEntity updateMember(Integer id, MemberEntity member);
    MemberEntity deleteMember(Integer id);
    List<RecipeEntity> findRecipesByMemberId(Integer memberId);
    List<CommentEntity> findCommentsByMemberId(Integer memberId);
    List<RatingEntity> findRatingsByMemberId(Integer memberId);
}
